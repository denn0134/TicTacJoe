package TicTacEngine;

import java.awt.*;

public class Line{
	private Point cell1, cell2, cell3;
	//constructor
	public Line(int x1, int y1, int x2, int y2, int x3, int y3){
		cell1 = new Point(x1, y1);
		cell2 = new Point(x2, y2);
		cell3 = new Point(x3, y3);
	}
	public Point getCell1(){
		return cell1;

	}
	public Point getCell2(){
		return cell2;

	}
	public Point getCell3(){
		return cell3;

	}
	public Point getIntersection(Line line){
		Point match = null;
		int iCount = 0;
		if (cell1.equals(line.cell1)||cell1.equals(line.cell2)||cell1.equals(line.cell3)){
			iCount++;
			match = cell1;

		}
		if (cell2.equals(line.cell1)||cell2.equals(line.cell2)||cell2.equals(line.cell3)){
			iCount++;
			match = cell1;

		}
		if (cell3.equals(line.cell1)||cell3.equals(line.cell2)||cell3.equals(line.cell3)){
			iCount++;
			match = cell1;

		}
		if(iCount == 1)
			return match;
		else
			return null;

	}

}