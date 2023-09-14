package ru.theone_ss.banilla_hammers.entity;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import ru.theone_ss.banilla_hammers.registry.BanillaHammersEffects;
import ru.theone_ss.banilla_hammers.registry.BanillaHammersEntities;

import java.util.List;

public class RectangleEntity extends Entity {
    public int duration = 8;
    public PlayerEntity player = null;
    public RectangleEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    public RectangleEntity(World world, double x, double y, double z, PlayerEntity player) {
        this(BanillaHammersEntities.RECTANGLE, world);
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.player = player;
        setVelocity(player, player.getPitch(), player.getYaw(), player.getRoll());
    }

    @Override
    public void tick() {

        this.move(MovementType.SELF, this.getVelocity());
        World world = getWorld();
        if(!world.isClient){
            List<LivingEntity> list2 = world.getNonSpectatingEntities(LivingEntity.class, this.getBoundingBox());
            for(LivingEntity livingEntity : list2) {
                livingEntity.damage(world.getDamageSources().magic(), 2F);
                livingEntity.setVelocity(0,0.75,0);
                livingEntity.addStatusEffect(new StatusEffectInstance(BanillaHammersEffects.STUN, 7 * 20, 0, false, false, true), livingEntity);
            }
            if(duration != 0){
                duration--;
            }
            else {
                this.remove(RemovalReason.DISCARDED);
            }
            if(world.getBlockState(this.getBlockPos().down()).isAir()){
                if(!world.getBlockState(this.getBlockPos().down().down()).isAir()){
                    this.setPos(this.getX(), this.getY() -1, this.getZ());
                }
                else {
                    this.remove(RemovalReason.DISCARDED);
                }
            }
            boolean bl = false;
            Box box = this.getBoundingBox().expand(0.2);

            for(BlockPos blockPos : BlockPos.iterate(
                    MathHelper.floor(box.minX),
                    MathHelper.floor(box.minY),
                    MathHelper.floor(box.minZ),
                    MathHelper.floor(box.maxX),
                    MathHelper.floor(box.maxY),
                    MathHelper.floor(box.maxZ)
            )) {
                BlockState blockState = world.getBlockState(blockPos);
                Block block = blockState.getBlock();
                if (block instanceof CropBlock || block instanceof FernBlock || block instanceof SaplingBlock || block instanceof FlowerBlock || block instanceof TallPlantBlock) {
                    bl = world.breakBlock(blockPos, true, this) || bl;
                }
            }
            BlockState state = world.getBlockState(this.getBlockPos().down());
            if(duration % 2 == 0) {
                world.playSound(null, this.getX(), this.getY(), this.getZ(), state.getBlock().getSoundGroup(state).getBreakSound(), SoundCategory.BLOCKS, 1.3f, 1);
            }
        }
        if (world.isClient) {
            for(int a = 1; a < 16; a++){
                spawnSprintingParticles();
            }
            world.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 0.3, 0.1, 0.3);
        }
    }

    public void setVelocity(Entity shooter, float pitch, float yaw, float roll) {
        float f = -MathHelper.sin(yaw * (float) (Math.PI / 180.0)) * MathHelper.cos(pitch * (float) (Math.PI / 180.0));
        float g = -MathHelper.sin((pitch + roll) * (float) (Math.PI / 180.0));
        float h = MathHelper.cos(yaw * (float) (Math.PI / 180.0)) * MathHelper.cos(pitch * (float) (Math.PI / 180.0));
        this.setVelocity(f, g, h);
        Vec3d vec3d = shooter.getVelocity();
        this.setVelocity(this.getVelocity().add(vec3d.x, shooter.isOnGround() ? 0.0 : vec3d.y, vec3d.z));
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }
}
