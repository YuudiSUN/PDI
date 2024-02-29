package game;
import java.awt.Point;

//Strategy.class
public enum Strategy {
 // 激进的战略
 AGGRESSIVE {
     // 实现getTarget方法，返回最近的宝藏的坐标
     @Override
     public int[] getTarget(int x, int y, Map map) {
         // 定义一个长度为2的整数数组，用于存放目标坐标
         int[] target = new int[2];
         // 定义一个最小距离，初始为无穷大
         int minDistance = Integer.MAX_VALUE;
         // 遍历所有宝藏的坐标
         for (Point treasure : map.getTreasures()) {
             // 计算当前坐标和宝藏坐标的距离
             int distance = Math.abs(x - treasure.x) + Math.abs(y - treasure.y);
             // 如果距离小于最小距离，就更新目标坐标和最小距离
             if (distance < minDistance) {
                 target[0] = treasure.x;
                 target[1] = treasure.y;
                 minDistance = distance;
             }
         }
         // 返回目标坐标数组
         return target;
     }
 },
 // 保守的战略
 CONSERVATIVE {
     // 实现getTarget方法，返回最安全的坐标
     @Override
     public int[] getTarget(int x, int y, Map map) {
         // 定义一个长度为2的整数数组，用于存放目标坐标
         int[] target = new int[2];
         // 定义一个最大安全度，初始为0
         int maxSafety = 0;
         // 定义一个方向数组，表示上下左右四个方向
         int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
         // 遍历四个方向
         for (int[] direction : directions) {
             // 计算目标坐标
             int tx = x + direction[0];
             int ty = y + direction[1];
             // 判断目标坐标是否合法，即是否可以移动
             if (map.canMove(tx, ty)) {
                 // 如果合法，就计算目标坐标的安全度，即周围没有野生动物的数量
                 int safety = 0;
                 // 遍历四个方向
                 for (int[] dir : directions) {
                     // 计算周围的坐标
                     int nx = tx + dir[0];
                     int ny = ty + dir[1];
                     // 判断周围的坐标是否合法
                     if (map.canMove(nx, ny)) {
                         // 如果合法，就判断周围的网格是否是野生动物
                         if (map.getGrid()[nx][ny] != 3) {
                             // 如果不是野生动物，就安全度加一
                             safety++;
                         }
                     }
                 }
                 // 如果安全度大于最大安全度，就更新目标坐标和最大安全度
                 if (safety > maxSafety) {
                     target[0] = tx;
                     target[1] = ty;
                     maxSafety = safety;
                 }
             }
         }
         // 返回目标坐标数组
         return target;
     }
 };

 // 抽象的getTarget方法，传入当前坐标和地图对象，返回一个长度为2的整数数组
 public abstract int[] getTarget(int x, int y, Map map);
}
