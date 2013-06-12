/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalesman;

import java.util.Random;

/**
 *
 * @author PraveenNK
 */
public class RoadMap {
    private int numcities;
	private int[][] matrix;
	private final int maxdistance=10;
	
	public RoadMap(int pnumcities)
	{
		Random generator = new Random();
		this.matrix = new int[pnumcities][pnumcities];
		this.numcities = pnumcities;
		for(int i=0;i<pnumcities; i+=1)
		{
			for(int j=0;j<pnumcities; j+=1)
			{
				if (i==j)
				{
					this.matrix[i][j]=0;
				}
				else
				{
					this.matrix[i][j]=(generator.nextInt()%(maxdistance-1));
					if (this.matrix[i][j]<0)
					{
						this.matrix[i][j] = this.matrix[i][j]*-1;
					}
					this.matrix[i][j]+=1;
					
				}
				
				
			}
		}
	}
	
	public void Print()
	{
		
		for(int i=0;i<this.numcities; i+=1)
		{
			for(int j=0;j<this.numcities; j+=1)
			{
				if (this.matrix[i][j]==-1)
				{
					String s= "----";
					
					System.out.print(s+" ");
				}
				else
				{
					String s= "0000"+this.matrix[i][j];
				
					s = s.substring(s.length()-4); // keep the rightmost 4 chars (4 plus null char)
				
					System.out.print(s+" ");
				}
				if (j==(this.numcities-1))
				{
					System.out.println();
				}
				
				
			}
		}
		System.out.println();
	}
	
	public int NumCities()
	{
		return this.numcities;
	}
	
	public int GetDistanceFrom(int start, int finish)
	{
		return this.matrix[start][finish];
	}
    
}
