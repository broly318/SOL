package neobaka.neobaka;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;

import static java.lang.Thread.sleep;

public class SOL implements Listener {
    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack items = player.getInventory().getItemInMainHand();
        if (items.getType() == Material.BOOK) {
            Block target = getTargetBlock(player);
            if (target != null) {
                int amounts = items.getAmount();
                if (amounts >= 64) {
                    items.setAmount(amounts - 64);
                    player.sendMessage(ChatColor.BLUE + "————Ｓａｔｅｌｌｉｔｅ  ｉｎ  Ｏｒｂｉｔａｌ  Ｌａｓｅｒ-ｗｅａｐｏｎ");
                    double i = 0;
                    double k = 256;
                    while(k>=target.getY()) {
                        for (float p = 0; p < 360; p = (float) (p + 0.5)) {
                            (player.getWorld()).spawnParticle(Particle.END_ROD, (float)(target.getX()+ Math.sin(Math.toRadians(p)) * 2), (float) (k), (float) (target.getZ() + Math.cos(Math.toRadians(p)) * 2), 0,1, 0, 0, 0);
                        }
                        k=k-0.4;
                    }
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for(float o=0;o<360;o=(float) (o+0.5)){
                        (player.getWorld()).spawnParticle(Particle.CLOUD,(float) (target.getX()+Math.sin(Math.toRadians(o))*8), (float) (target.getY()+10), (float) (target.getZ()+Math.cos(Math.toRadians(o))*8), 1, 0, 0, 0, 0);
                    }
                    double l = 0;
                    while (l<15){
                        for(float m=0;m<360;m=(float) (m+0.5)){
                            (player.getWorld()).spawnParticle(Particle.EXPLOSION_LARGE,(float) (target.getX()+Math.sin(Math.toRadians(m))*l), (float) (target.getY()+1), (float) (target.getZ()+Math.cos(Math.toRadians(m))*l), 1, 0, 0, 0, 0);
                        }
                        l=l+1;
                    }
                    while (i < 10) {
                        target.getWorld().createExplosion(target.getX(), target.getY(), target.getZ(), 15, false, false);
                        i++;
                    }
                }
                else {
                    player.sendMessage(ChatColor.DARK_RED+"アイテムが足りなぁぁぁぁぁい！！");
                }
            }
        }
    }
    private Block getTargetBlock(Player player) {
        BlockIterator it = new BlockIterator(player, 100);
        while ( it.hasNext() ) {
            Block block = it.next();
            if ( block.getType() != Material.AIR ) {
                return block;
            }
        }
        return null;
    }
}