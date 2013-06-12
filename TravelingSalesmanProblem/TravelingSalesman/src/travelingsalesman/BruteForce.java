/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalesman;
import java.util.*;
/**
 *
 * @author PraveenNK
 */
public class BruteForce {
    public class Road
	{
		public int from;
		public int to;
	};
	
	private RoadMap mymap;
	ArrayList<Road> bestroute;
	
	public BruteForce(RoadMap map)
	{
		mymap = map;
		bestroute = new ArrayList<Road>();
	}
	
	public boolean CalcBestRoute()
	{
		ArrayList<Road> route = new ArrayList<Road>();
		TryRoutes(route);
		
		return true;
	}
	
	public void Print()
	{
		System.out.print("Best ");
		Print(this.bestroute);
	}
	
	public void Print(ArrayList<Road> route)
	{
		System.out.println("Route Distance: "+RouteDistance(route));
		System.out.println("=======================================================");
		for(int i=0;i<route.size();i+=1)
		{
			Road road = route.get(i);
			System.out.println("road "+i+" from "+road.from+" to "+road.to+" distance "+this.mymap.GetDistanceFrom(road.from, road.to));
		}
		System.out.println("");
	}
	
	private int TryRoutes(ArrayList<Road> route)
	{
		if (route.size()==this.mymap.NumCities()-1 )
		{
			//Print(route);
			if (IsBestRoute(route)==true)
			{
				bestroute=route;
				
			}
		}
		else
		{

			
			for(int i=0;i<this.mymap.NumCities();i+=1)
			{
				Road nextroad = new Road();
				if (route.isEmpty()==true)
				{
					nextroad.from=0;
				}
				else
				{
					Road lastroad = route.get(route.size()-1);
					nextroad.from = lastroad.to;
				}
				nextroad.to = i;
				                                                                             //make sure we visit new cities only
				if (i==nextroad.from)
				{
					continue;
				}
				if (CityIsOnRoute(route, nextroad.to))
				{
					continue;
				}
				ArrayList<Road>testroute = new ArrayList<Road>(route);
				testroute.add(nextroad);
				TryRoutes(testroute);
			}
		}
		return 0;
	}
	
	private boolean IsBestRoute(ArrayList<Road> route)
	{
		if ( RouteDistance(this.bestroute) > RouteDistance(route) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private int RouteDistance(ArrayList<Road> route)
	{
		int distance = 0;
		
		if (route.isEmpty()==true)
		{
			return java.lang.Integer.MAX_VALUE;
		}
		
		for(int i=0;i<route.size();i+=1)
		{
			Road road = route.get(i);
			distance += this.mymap.GetDistanceFrom(road.from, road.to);
		}
		return distance;
	}
	
	private boolean CityIsOnRoute(ArrayList<Road> route, int city)
	{
		for(int i=0;i<route.size();i+=1)
		{
			Road road = route.get(i);
			if (road.from==city || road.to==city)
			{
				return true;
			}
		}
		return false;
	}
    
}
