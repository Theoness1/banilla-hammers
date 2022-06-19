package ru.theone_ss.banilla_hammers.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.theone_ss.banilla_hammers.entity.RectangleEntity;

public class HammerItem extends AxeItem {

    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    public HammerItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
        float attackDamage1 = attackDamage + material.getAttackDamage();

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", attackDamage1, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier("Weapon modifier", 1, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }


    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if(!world.isClient) {
            BlockPos pos = context.getBlockPos().up();
            RectangleEntity rectangle = new RectangleEntity(context.getWorld(), pos.getX(), pos.getY(), pos.getZ(), context.getPlayer());
            world.spawnEntity(rectangle);
            context.getPlayer().getItemCooldownManager().set(context.getStack().getItem(), 640);
            ItemStack stack = context.getPlayer().getStackInHand(context.getHand());
            stack.setDamage(stack.getDamage()+4);
        }
        return ActionResult.success(world.isClient);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }
}
