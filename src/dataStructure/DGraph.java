package dataStructure;

import algorithms.Graph_Algo;
import utils.Point3D;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Fixes:
 * need to fix nullpoint if get edge.* or node.* throws nullpointexception.
 */


public class DGraph implements graph{
	private HashMap<Integer,node_data> Nodemap= new HashMap<>();
	private HashMap<Integer,HashMap<Integer,edge_data>> Edgemap= new HashMap<>();
	private int edgeSize;
	private int nodeSize;
	private int MC;


	public DGraph(){
		this.Nodemap= new HashMap<>();
		this.Edgemap = new HashMap<>();
		this.edgeSize=0;
		this.nodeSize=0;
		this.MC=0;
	}


	public node_data getNode(int key) {
		try {
			return this.Nodemap.get(key);
		}
		catch (Exception e){

			throw new RuntimeException(" the Node isnt exist.");
		}
	}


	public edge_data getEdge(int src, int dest) {
		try {
			return this.Edgemap.get(src).get(dest);
		}
		catch(NullPointerException e){
			return null;
		}
	}


	public void addNode(node_data n) {
		this.Nodemap.put(n.getKey(),n);
		this.nodeSize++;
		this.MC++;
	}


	public void connect(int src, int dest, double w) {
		edge_data temp = new Edge(src,dest,w);
		if(this.getNode(src)!=null && this.getNode(dest)!=null) {  // the nodes isnt exist.
			if (this.getEdge(src, dest) == null) {  // the edge isnt exist.
				if(this.Edgemap.get(src) == null) {   // the Hashmap of neighburs isnt exist
					HashMap<Integer, edge_data> add = new HashMap<>();
					this.Edgemap.put(src,add);
					this.Edgemap.get(src).put(dest,temp);
				}
				else {         //hash map of neighburs exist but the edge isnt.
					this.Edgemap.get(src).put(dest, temp);
				}
			}
		}
		else {
			System.err.println("Wrong input,Missing Nodes.");
			return;
		}
		this.edgeSize++;
		this.MC++;
	}


	public Collection<node_data> getV() {
		return this.Nodemap.values();
	}


	public Collection<edge_data> getE(int node_id) {
		try {
			return this.Edgemap.get(node_id).values();
		}
		catch (NullPointerException e){
			return null;
		}
	}


	public node_data removeNode(int key) {
		node_data removed = this.getNode(key);
		if(removed!=null){
			this.Nodemap.remove(key);
			this.nodeSize--;
			if(this.Edgemap.get(key)!=null)this.Edgemap.remove(key);
			Iterator iterator = this.Edgemap.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry map =(Map.Entry)iterator.next();
				int key2=(int) map.getKey();
				if(this.Edgemap.get(key2).get(key)!=null){
					removeEdge(key2,key);
				}
			}
		}
		return removed;
	}


	public edge_data removeEdge(int src, int dest) {
		edge_data temp =this.getEdge(src,dest);
		if(temp!=null) {
			this.Edgemap.get(src).remove(dest);
			this.edgeSize--;
			this.MC++;
			return temp ;
		}
		System.err.println("Edge isnt exist, for src:" + src + ",  dest:" + dest);
		return temp;
	}


	public int nodeSize() {
		return this.nodeSize;
	}


	public int edgeSize() {
		return this.edgeSize;
	}


	public int getMC() {
		return this.MC;
	}

	public HashMap getHash(){ return this.Nodemap; }

	public HashMap getEdgeHash(){return this.Edgemap;}

	public static void main(String[] args) {
		Point3D x = new Point3D(14,4,0);
		Point3D y = new Point3D(-75,14,0);
		Point3D q = new Point3D(80,5,0);
		Point3D x1 = new Point3D(14,4,0);
		Point3D y2 = new Point3D(-75,14,0);
		Point3D q3 = new Point3D(80,5,0);
		node_data a = new Node(1,2,3,x,"asf");
		node_data b = new Node(2,4,6,y,"ads");
		node_data c = new Node(3,50,50,q,"sf");
		node_data a1 = new Node(4,2,3,x,"asf");
		node_data b2 = new Node(5,4,6,y,"ads");
		node_data c3 = new Node(6,50,50,q,"sf");
		DGraph d = new DGraph();
		d.addNode(a);
		d.addNode(b);
		d.addNode(c);
<<<<<<< HEAD
		d.connect(a.getKey(),b.getKey(),4);
		d.connect(b.getKey(),c.getKey(),50);
		d.connect(c.getKey(),b.getKey(),25);
		d.connect(b.getKey(),a.getKey(),13);
		System.out.println(d.Edgemap.toString());
=======
		d.addNode(a1);
		d.addNode(b2);
		d.addNode(c3);
		d.connect(a.getKey(),b.getKey(),2);
		d.connect(a.getKey(),a1.getKey(),4);
		d.connect(a.getKey(),c.getKey(),1);
		d.connect(c.getKey() ,b2.getKey(),1);
		d.connect(b2.getKey(),a1.getKey(),1);
		d.connect(a1.getKey(),c3.getKey(),1);
		d.connect(b.getKey(),c3.getKey(),1);

>>>>>>> 3bad1d0d8735f11060cfadd106bfbd1a8137a056
		Graph_Algo p = new Graph_Algo(d);
		System.out.println(p.shortestPathDist(1,6));

//		d.removeNode(1);
//		d.removeEdge(1,3);
//		edge_data t = d.getEdge(1,3);
//		System.out.println(d.Edgemap);
//		System.out.println(d.Nodemap);
	}

}
