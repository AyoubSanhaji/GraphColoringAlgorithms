import java.util.*;

public class Dijkstra 
{
	
	//La remplition de la matrice de coût
	public int [][] mat()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Entre le nb de sommets: ");
		int dim = sc.nextInt();
		int [][] mat = new int [dim][dim];
		System.out.println("Entrer les arcs:\n100 s'il n'exist pas");
		for(int i=0 ; i<dim ; i++)
			for(int j=0 ; j<dim ; j++)
			{
				System.out.println("Arc("+(i+1)+" , "+(j+1)+") = ");
				mat[i][j] = sc.nextInt();
			}
		return mat;
	}
	
	public void affiche(int [][] t)
	{
		int dim1 = t.length;
		int dim2 = t[0].length;
		System.out.println("Le tableau est: \n");
		for(int i=0 ; i<dim1 ; i++)
		{
			for(int j=0 ; j<dim2 ; j++)
				System.out.print(t[i][j]+"\t");
			System.out.println();
		}
	}
	
	public void afficher(int [] t)
	{
		int dim = t.length;
		System.out.println("Le tableau est: \n");
		for(int i=0 ; i<dim ; i++)
			System.out.print(t[i]+"\t");
	}
	
	//a appartient à t ou pas
	public boolean app(int a , int []t)
	{
		for(int i=0 ; i<t.length ; i++)
			if(t[i] == a)
				return true;
		return false;
	}
	
	//Le tableau est vide ou non
	public boolean vide(int t[])
	{
		int nb=0;
		for(int i=0 ; i<t.length ; i++)
			if(t[i] == 0)
				nb++;
		if(nb == t.length)
			return true;
		else return false;
	}
	
	//Retire la valeur a du tableau t
	public void retire(int a , int [] t)
	{
		for(int i=0 ; i<t.length ; i++)	
			if(a == t[i])
				t[i]=0;
	}
	
	//Fonction Dijkstra
	public void Dij(int mat[][])
	{
		int s[] , sb[] , p[] , d[] , ind=0  , t=0;
		int dim = mat.length;
		s = new int [dim];
		sb = new int [dim];
		p = new int [dim];
		d = new int [dim];
		
		System.out.println("Donner le sommet source: ");
		Scanner sc  = new Scanner(System.in);
		int src = sc.nextInt();
		s[ind] = src;
		ind++;
		
		for(int i=0 ; i<dim ; i++)
			sb[i] = i+1;
		this.retire(src, sb);
		
		for(int i=0 ; i<dim ; i++)
			if(this.app(i+1, sb) == true)
			{
				p[i] = src;
				d[i] = mat[src-1][i];
			}
		
		
		for(int k=0 ; (k<dim)&&(this.vide(sb) == false) ; k++)
		{	
			int c=0;
			while(c<dim){
				if(this.app((c+1), sb) == true)
				{
					int min = d[c];
					t=c+1;	
					for(int j=c+1 ; j<dim ; j++)
						if(this.app((j+1), sb) == true)
							if(min > d[j])
							{
								min = d[j];
								t = j+1;
							}
					c=dim;
				}
				else
					c++;
			}
			s[ind] = t;
			this.retire(t, sb);
			ind++;
			
			
			for(int i=0 ; i<dim ; i++)
				if(this.app(i+1, sb) == true)
					if((mat[t-1][i] != 0)&&(mat[t-1][i] != 100))
						if(d[t-1]+mat[t-1][i] < d[i])
						{
							d[i] = d[t-1] + mat[t-1][i];
							p[i] = t;
							
						}
		}
		
		
		System.out.println("Donner le sommet destinataire: ");
		int des = sc.nextInt();	
		
		
		//Supprime les doublant
		for(int i=0 ; i<dim ; i++)
			for(int j=0 ; j<i ; j++)
				if(p[i] == p[j])
					p[j]=0;
	
		//Affichage de chemin et de son cout
		System.out.println("Le plus court chemin est: ");
		for(int i=0 ; i<dim ; i++)
			if(p[s[i]-1] != 0)
				System.out.print(p[s[i]-1]+" -> ");
		
		if(p[s[dim-1]-1] != des)
			System.out.println(des+" -> fin");
		
		else 
			System.out.println("fin");

		System.out.println("Son cout est: "+d[des-1]);
	}
	
	public static void main(String []args)
	{
		Dijkstra d = new Dijkstra();
		int [][]matn;
		matn = d.mat();
		d.Dij(matn);
	}
}