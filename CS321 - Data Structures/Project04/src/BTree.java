import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * CS321 B Tree Project - BTree
 * This class creates the data structure, b tree, and contains several methods.
 * @author Ahram, Max, Ella
 *
 */
public class BTree {

	public BTreeNode root;
	public int degree;
	public RandomAccessFile raf;
	public GeneBankConvert convert;
	private int position;

	/**
	 * Constructor - degree should be given from the user and will create a b tree binary file.
	 * @param degree - degree for b tree 
	 * @param fileName - b tree binary file 
	 * @throws IOException
	 */
	public BTree(int degree, String fileName) throws IOException
	{
		File file = null;
		file = new File(fileName);
		if(file.exists()) {
			file.delete();
		}
		//using random access file to read and write binary file.
		this.raf = new RandomAccessFile(file, "rw");
		writeMetaData();
		this.degree = degree;	
		this.root = allocateNode();	
		this.convert = new GeneBankConvert();
	}

	/**
	 * Constructor 2 - this constructor is for b tree search class.
	 * @param fileName
	 * @throws IOException
	 */
	public BTree(String fileName) throws IOException
	{
		File file = new File(fileName);
		this.raf = new RandomAccessFile(file, "rw");
		readMetaData();
		this.root = diskRead(position);
	}

	/**
	 * allocate the node 
	 * @return BTreeNode
	 */
	public BTreeNode allocateNode() throws IOException
	{
		BTreeNode newNode = new BTreeNode(degree);
		newNode.setPosition((int)raf.length());
		diskWrite(newNode);
		return newNode;
	}

	/**
	 * BTreeSearch 
	 * This function searches a key value in the b tree.
	 * @param node - starting point
	 * @param key - searching for
	 * @return TreeObject - searched 
	 */
	public TreeObject bTreeSearch(BTreeNode node, TreeObject key) 
	{
		int i = 0;
		BTreeNode childNode = null;
		while( i < node.getNumOfObjects() && key.getKey() > node.objects[i].getKey()) 
		{
			i = i + 1;
		}
		
		if( i < node.getNumOfObjects() && key.getKey() == node.objects[i].getKey())
		{
			return node.objects[i];
		}
		else if(node.isLeaf) 
		{
			return null;
		}
		else
		{
			try {
				childNode = diskRead(node.pointers[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bTreeSearch(childNode, key);
		}
	}
	
	/**
	 * This search method is saving frequency data to the disk
	 * @param node
	 * @param key
	 * @return TreeObject
	 * @throws IOException
	 */
	private TreeObject bTreeSearch2(BTreeNode node, TreeObject key) throws IOException
	{
		if(key.getFrequency() == 0) {
			return null;
		}
		int i = 0;
		BTreeNode childNode = null;
		while( i < node.getNumOfObjects() && key.getKey() > node.objects[i].getKey()) 
		{
			i = i + 1;
		}
		if( i < node.getNumOfObjects() && key.getKey() == node.objects[i].getKey())
		{
			node.objects[i].setFrequency(node.objects[i].getFrequency()+1);

			diskWrite(node);
			return node.objects[i];
		}
		else if(node.isLeaf) 
		{
			return null;
		}
		else 
		{
			childNode = diskRead(node.pointers[i]);
			return bTreeSearch2(childNode, key);
		}
	}

	/**
	 * This method inserts tree object to the b tree
	 * @param k - tree object need to be inserted
	 * @throws IOException
	 */
	public void bTreeInsert(TreeObject k) throws IOException
	{
		BTreeNode r = this.root;
		TreeObject dup = bTreeSearch2(root, k);

		if(dup != null) 
		{
			return;
		}
		
		if(r.getNumOfObjects() == (2*degree-1))
		{
			BTreeNode s = allocateNode();
			this.root = s;
			s.isLeaf = false;
			s.setNumOfObjects(0);
			this.position = s.getPosition();
			s.pointers[0] = r.getPosition();
			bTreeSplitChild(s, 0);
			bTreeInsertNonfull(s, k);
		}		
		else 
		{
			bTreeInsertNonfull(r, k);
		}
	}
		
	/**
	 * This method inserts the tree object to the node which is not full.
	 * @param node - BTreeNode
	 * @param k - TreeObject
	 * @throws IOException
	 */
	public void bTreeInsertNonfull(BTreeNode node, TreeObject k) throws IOException
	{
		BTreeNode childNode = null;
		int i = node.getNumOfObjects()-1;
		if(node.isLeaf) 
		{
			while(i >= 0 && k.getKey() < node.objects[i].getKey()) 
			{
				node.objects[i+1].setKey(node.objects[i].getKey());
				node.objects[i+1].setFrequency(node.objects[i].getFrequency());
				i = i - 1;
			}
			node.objects[i+1].setKey(k.getKey());
			node.objects[i+1].setFrequency(k.getFrequency());
			node.setNumOfObjects(node.getNumOfObjects()+1);
			diskWrite(node);
		}
		else
		{
			while(i >= 0 && k.getKey() < node.objects[i].getKey())
			{
				i--;
			}
			i++;

			childNode = diskRead(node.pointers[i]);

			if(childNode.getNumOfObjects() == (2*degree-1)) 
			{
				bTreeSplitChild(node, i);
				if(k.getKey() > node.objects[i].getKey()) 
				{
					i++;
				}
			}
			childNode = diskRead(node.pointers[i]);
			bTreeInsertNonfull(childNode, k);
		}
	}

	/**
	 * This method splits the node to two nodes because the node is full
	 * @param node
	 * @param i
	 */
	public void bTreeSplitChild(BTreeNode node, int i) throws IOException
	{
		BTreeNode z = allocateNode();
		BTreeNode y = new BTreeNode(degree);
		y = diskRead(node.pointers[i]);

		z.isLeaf = y.isLeaf;
		z.setNumOfObjects(degree-1);
		for(int j = 0; j <= degree-2; j++) 
		{
			z.objects[j].setKey(y.objects[j+degree].getKey());
			z.objects[j].setFrequency(y.objects[j+degree].getFrequency());
		}
		if(!y.isLeaf) 
		{
			for(int k = 0; k <= degree-1; k++) 
			{			
				z.pointers[k] = y.pointers[k+degree];
			}
		}
		y.setNumOfObjects(degree-1);

		for(int l = node.getNumOfObjects(); l >= i+1; l--)
		{
			node.pointers[l+1] = node.pointers[l];
		}
		node.pointers[i+1] = z.getPosition();

		for(int m = node.getNumOfObjects()-1; m >= i; m--) 
		{
			node.objects[m+1].setKey(node.objects[m].getKey());
			node.objects[m+1].setFrequency(node.objects[m].getFrequency());
		}
		
		node.objects[i].setKey(y.objects[degree-1].getKey());
		node.objects[i].setFrequency(y.objects[degree-1].getFrequency());
		node.setNumOfObjects(node.getNumOfObjects()+1);

		for(int b = y.getNumOfObjects(); b < y.objects.length; b++)
		{
			y.objects[b].setKey(0);
			y.objects[b].setFrequency(0);
		}

		for(int c = degree; c < y.pointers.length; c++)
		{
			y.pointers[c] = 0;
		}
		//write to the disk
		diskWrite(y);
		diskWrite(z);
		diskWrite(node);
	}

	/**
	 * Creating an empty B-tree
	 * @param T - BTree
	 */
	public void bTreeCreate() throws IOException
	{
		BTreeNode newNode = allocateNode();
		newNode.isLeaf = true;
		newNode.setNumOfObjects(0);
		diskWrite(newNode);
		this.root = newNode;
	}

	/**
	 * This method is for printing out the result as in order traversal order.
	 * @param node
	 * @param seqLength
	 * @param fw
	 * @throws IOException
	 */
	public void inOrderTraversal(BTreeNode node, int seqLength, FileWriter fw) throws IOException
	{
		GeneBankConvert convert = new GeneBankConvert();
		
		if(node.isLeaf())
		{
			for(int i = 0; i < node.getNumOfObjects(); i++)
			{
				fw.write(convert.convertLongToString(node.objects[i].getKey(), seqLength) + " : ");
				fw.write(node.objects[i].getFrequency() + "\n");
			}
		}
		
		else
		{
			for(int j = 0; j < node.getNumOfObjects(); j++)
			{
				BTreeNode n = diskRead(node.pointers[j]);
				inOrderTraversal(n, seqLength, fw);
				if(j < node.getNumOfObjects())
				{
					fw.write(convert.convertLongToString(node.objects[j].getKey(), seqLength) + " : ");
					fw.write(node.objects[j].getFrequency() + "\n");
				}
			}
			
			try {
				BTreeNode n = diskRead(node.pointers[node.getNumOfObjects()]);
				inOrderTraversal(n, seqLength, fw);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Closing the b tree. Need to write the root information again.
	 * @throws IOException
	 */
	public void bTreeClose() throws IOException 
	{
		diskWrite(this.root);
		writeMetaData();
		raf.close();
	}

	/**
	 * Reading the disk
	 * @param position
	 * @return
	 * @throws IOException
	 */
	public BTreeNode diskRead(int position) throws IOException 
	{
		BTreeNode readNode = null;
		readNode = new BTreeNode(degree);
		readNode.setPosition(position);
		raf.seek(readNode.getPosition());

		readNode.isLeaf = raf.readBoolean();
		readNode.setNumOfObjects(raf.readInt());
		
		for(int i = 0; i < readNode.objects.length; i++) 
		{
			readNode.objects[i].setKey(raf.readLong());
			readNode.objects[i].setFrequency(raf.readInt());
		}
		
		for(int j = 0; j < readNode.pointers.length; j++)
		{
			readNode.pointers[j] = raf.readInt();
		}
		return readNode;
	}

	/**
	 * Writing to the disk
	 * @param node
	 * @throws IOException
	 */
	public void diskWrite(BTreeNode node) throws IOException
	{
		raf.seek(node.getPosition()); 
		raf.writeBoolean(node.isLeaf);
		raf.writeInt(node.getNumOfObjects());

		for(int i = 0; i < node.objects.length; i++)
		{
			TreeObject obj = node.objects[i];
			raf.writeLong(obj.getKey());
			raf.writeInt(obj.getFrequency());
		}
		for(int j = 0; j < node.pointers.length; j++) 
		{
			raf.writeInt(node.pointers[j]);
		}
		
	}
	
	/**
	 * Reading the meta data - starts from 0
	 * @throws IOException
	 */
	public void readMetaData() throws IOException
	{
		raf.seek(0);
		degree = raf.readInt();
		position = raf.readInt();
	}

	/**
	 * Writing the meta data - starts from 0
	 * @throws IOException
	 */
	public void writeMetaData() throws IOException
	{
		raf.seek(0);
		raf.writeInt(degree);
		raf.writeInt(position);
	}

	/**
	 * Writing the node meta data
	 * @param node
	 * @param position
	 * @throws IOException
	 */
	public void writeNodeMetaData(BTreeNode node, int position) throws IOException
	{
		raf.seek(position);
		raf.writeBoolean(node.isLeaf);
		raf.writeInt(node.getNumOfObjects());
	}

	@Override
	public String toString() 
	{
		return "BTree [root=" + root + ", degree=" + degree + ", raf=" + raf + "]";
	}

}