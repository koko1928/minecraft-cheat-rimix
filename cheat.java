import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "mymod", name = "My Mod", version = "1.0")
public class MyMod {

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            event.setAmount(0);
        }
    }

    @SubscribeEvent
    public void isFloating(PlayerInteractEvent.RightClickBlock event) {
        if (event.getEntityPlayer().isSneaking()) {
            event.getEntityPlayer().motionY = 0.1;
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onBlockActivated(PlayerInteractEvent.RightClickBlock event) {
        ItemStack stack = event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND);
        if (!stack.isEmpty()) {
            event.getWorld().destroyBlock(event.getPos(), false);
            event.getEntityPlayer().inventory.addItemStackToInventory(stack);
            event.setCanceled(true);
        }
    }
}
