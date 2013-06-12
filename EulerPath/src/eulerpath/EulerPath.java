/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eulerpath;

/**
 * @author PraveenNK
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EulerPath {

	private static int nodes;
	private static int edges = 0;
	private static int[][] graph;
	private static int[][] evalMat;
	private static int[] edgeVisited;
	private static int[] nodeVisited;
        private static long start,end;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.print("Enter the number of nodes ");
		nodes = getInt();
		System.out.println((int)(Math.random()*4));
		
		init();
		
		int result = connection();		
		
		while(result==0)
		{
			edges = 0;
			init();
			result = connection();
		}
		
		System.out.println("The Graph is");
		for(int i=0;i<nodes;i++)
		{
			for(int j=0;j<nodes;j++)
			{
				System.out.print(" "+graph[i][j]);
			}
			System.out.println();
		}
		
		result = euler();
		
	    if(result==1)
	    {
	        System.out.println("And The path is: ");
	        for(int i=0; i<edges+1; i++)
	        {
	        	System.out.print(nodeVisited[i] + " ");
	        }
                end = System.currentTimeMillis();
                System.out.println("time taken:"+ (end-start)+" ms");
	    }
	    else
	    	System.out.println("For the given graph No Euler Path");
                end = System.currentTimeMillis();
                System.out.println("time taken:"+ (end-start)+" ms");
        }
	
	public static void init()
	{
                start = System.currentTimeMillis();
		graph = new int[nodes][nodes];
		evalMat = new int[nodes][nodes];
		
		for(int i=0;i<nodes;i++)
		{
			for(int j=i;j<nodes;j++)
			{
				if(i==j)
					graph[i][j] = 0;
				else
				{
					graph[i][j] = Math.random()>0.5?1:0;
					graph[j][i] = graph[i][j];
					if(graph[i][j]!=0)
						edges++;
				}
			}
		}
		edgeVisited = new int[nodes*(nodes-1)/2];
		nodeVisited = new int[edges+1];
		
	    for(int i=0; i<nodes*(nodes-1)/2; i++)
	    {
	        edgeVisited[i] = 0;
	    }
	    
	    for(int i=0;i<nodes;i++)
	    {
	        for(int j=i+1;j<nodes;j++)
	        {
	            if(graph[i][j]==0)
	            {
	                edgeVisited[j*(j-1)/2+i] = -1;
	            }
	        }
	    }
	    for(int i=0; i<edges+1; i++)
	    {
	        nodeVisited[i] = 0;
	    }		

	}
	
	public static int connection()
	{
	    int i,j,m;
	    int result = 1;

	    
	    for(i=0; i<nodes; i++)
	    {
	        for(j=0; j<nodes; j++)
	        {
	            evalMat[i][j] = graph[i][j];
	        }
	    }
	    //check if the graph is a connected graph or not
	    for(i=0; i<nodes; i++)
	    {
	        for(j=0; j<nodes; j++)
	        {
	            if(evalMat[i][j]==1)
	            {
	                for(m=0; m<nodes; m++)
	                {
	                    if(evalMat[m][i]==1)
	                        evalMat[m][j] = 1;
	                }
	            }
	        }
	    }

	    for(i=0; i<nodes; i++)
	    {
	        for(j=0; j<nodes; j++)
	        {
	            if(evalMat[i][j]==0)
	            {
	                result = 0;
	                j = nodes;
	                i = nodes;
	            }
	        }
	    }
	    return result;
	}
	
	public static int euler()
	{
	    int i,j;
	    //save all the number of edges that are in one column
	    int temp = 0;
	    int odd = 0;
	    //save the starting point
	    int start = -1;
	    int end = -1;
	    int current = -1;

	    for(i=0; i<nodes; i++)
	    {
	        for(j=0; j<nodes; j++)
	        {
	            if(graph[i][j]!=0)
	                temp++;
	        }
	        //we find that, there is odd degrees from a node
	        if(temp%2!=0)
	        {
	            temp = 0;
	            odd++;
	            if(odd>2)
	                return 0;
	            else if(start==-1)
	                start = i;
	            else
	                end = i;
	        }
	    }
	    nodeVisited[0] = start;
	    current = start;
	    j = 0;
	    while(test()==0)
	    {
	        for(i=0; i<nodes; i++)
	        {
	            //nodes are connected
	            if(graph[current][i]!=0)
	            {
	                if(i>current)
	                {
	                    //edge is not visited
	                    if(edgeVisited[(i*(i-1))/2+current]==0)
	                    {
	                        //visited
	                        edgeVisited[(i*(i-1))/2+current] = 1;
	                        //save the node into path array
	                        nodeVisited[++j] = i;
	                        current = i;
	                        i = nodes;
	                    }
	                }
	                else
	                {
	                    //the edge is not visited
	                    if(edgeVisited[(current*(current-1))/2+i]==0)
	                    {
	                        //visited
	                        edgeVisited[(current*(current-1))/2+i] = 1;
	                        //save the node into path array
	                        nodeVisited[++j] = i;
	                        current = i;
	                        i = nodes;
	                    }
	                }

	            }
	        }
	    }
	    System.out.println("Start point: " + start);
	    System.out.println("End point: " + end);
	    return 1;
	}

	public static int test()
	{
	    int i;

	    for(i=0; i<nodes*(nodes-1)/2; i++)
	    {
	        //edge is not visited
	        if(edgeVisited[i]==0)
	            return 0;
	    }
	    return 1;
	}
	
	public static int getInt() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return Integer.parseInt(s);
	}

}
