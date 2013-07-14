import java.util.ArrayList;


public class CookieChips {

	/**
	 * @param args
	 */
	static class Point{
		double x;
		double y;
		public Point(double x, double y)
		{
			this.x=x;
			this.y=y;
		}
		
		
	}
	public static void main(String[] args) {
		int[][] centerTests = new int[51][51];
		/*s
		4.0 4.0
		4.0 5.0
		5.0 6.0
		1.0 20.0
		1.0 21.0
		1.0 22.0
		1.0 25.0
		1.0 26.0
		*/
		Point point1 = new Point(4.0 ,4.0);
		Point point2 = new Point(4.0 ,5.0);
		Point point3 = new Point(5.0 ,6.0);
		Point point4 = new Point(1.0 ,20.0);
		Point point5 = new Point(1.0 ,21.0);
		Point point6 = new Point(1.0 ,22.0);

		Point point7 = new Point(1.0 ,22.0);

		Point point8 = new Point(1.0 ,25.0);

		Point point9 = new Point(1.0 ,26.0);
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(point1);
		points.add(point2);
		points.add(point3);
		points.add(point4);
		points.add(point5);
		points.add(point6);
		points.add(point7);
		points.add(point8);
		points.add(point9);
		for (int i = 0; i < centerTests.length; i++) {
			for (int j = 0; j < centerTests[i].length; j++) {
				for(Point point : points)
				{
				  if(Math.pow((point.x-i), 2) + Math.pow((point.y-j), 2)<=6.25)
				  {
				     centerTests[i][j]+=1;
				  }
				}
				
				
				
			}
		}
		int max = Integer.MIN_VALUE;
		
				for (int i = 0; i < centerTests.length; i++) {
			for (int j = 0; j < centerTests[i].length; j++) {
				if( centerTests[i][j]>max)
				{
					max= centerTests[i][j];
				}
				
				
				
			}
		}
		
		System.out.println(max);

	}

}
