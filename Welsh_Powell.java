import java.util.*;

public class Algo 
{
	//La remplition de la matrice de coût
	public int [][] mat()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Entre le nb de sommets: ");
		int dim = sc.nextInt();
		int [][] mat = new int [dim][dim];
		System.out.println("Entrer les aretes:\n1 s'elle existe - 0 sinon");
		for(int i=0 ; i<dim ; i++)
			for(int j=0 ; j<dim ; j++)
			{
				System.out.println("Arete("+(i+1)+" , "+(j+1)+") = ");
				mat[i][j] = sc.nextInt();
			}
		return mat;
	}
	
	//Affichage d'une matrice
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
	
	//Affichage d'un tableau
	public void afficher(int [] t)
	{
		int dim = t.length;
		System.out.println("Le tableau est: \n");
		for(int i=0 ; i<dim ; i++)
			System.out.print(t[i]+"\t");
	}
	
	//Tableau des degrees des sommets
	public int [][] degree(int t [][])
	{
		int dim = t.length;
		int deg[][] = new int [2][dim];
		for(int i=0 ; i<dim ; i++)
		{
			deg[0][i] = (i+1);
			deg[1][i] = 0;
		}
		for(int i=0 ; i<dim ; i++)
			for(int j=0 ; j<dim ; j++)
				deg[1][i] += t[i][j];
		return deg;
	}
	
	//Le tri de tableau (Decroissement)
	public void tri(int t[][])
	{
		int dim = t[0].length;
		for(int j=0 ; j<dim ; j++)
		{	
			int max = t[1][j];
			int ind = j;
			for(int i=j+1 ; i<dim ; i++)
			{
				if(max < t[1][i])
				{	
					max = t[1][i];
					ind = i;
				}
			}
			int a = t[1][ind];
			int b = t[0][ind];
			t[1][ind] = t[1][j];
			t[0][ind] = t[0][j];
			t[1][j] = a;
			t[0][j] = b;
		}
	}
	
	//Fonction de Welsh et Powell
	public void ada(int deg[][] , int mat[][])
	{
		int ind , k=0 , m=0 , test[] , nb=0;
		int dim = mat.length;
		int [] degn = new int [deg[0].length];
		for(int i=0 ; i<deg[0].length ; i++)
			degn[i]=deg[0][i];
		for(k=0 ; (k<deg[0].length)||(m<deg[0].length) ; k++)
		{
			ind=0;
			if(degn[k] !=0 )
			{
				test = new int [dim]; 
				test [ind] = degn [k];
				ind++;
				int l = (degn[k]) - 1;
				for(int i=0 ; (i<dim) ; i++)
					if((mat[l][i] == 0)&&(i!=l))
						for(int j=0 ; j<degn.length ; j++)
							if(degn[j] == (i+1))
							{
								test[ind] = i+1;
								ind++;
							}
			
			for(int i=0 ; i<dim ; i++)
				for(int j=i+1 ; j<dim ; j++)
				{
					int b = test[i] - 1;
					int c = test[j] - 1;
					if((b>=0)&&(c>=0))
						if(mat[b][c] != 0)
							test[j] = 0;
				}
				
					
			for(int i=0 ; i<degn.length ; i++)
				for(int j=0 ; j<test.length ; j++)
					if(degn[i] == test[j])
						degn[i] = 0;
				
			System.out.print("S "+(nb+1)+" = { ");
			for(int i=0 ; i<ind ; i++)
				if(test[i] != 0)
				{
					System.out.print(test[i]+" ");
					m++;
				}
			System.out.println("}");
			nb++;
			}
		}
		System.out.println("Le nombre Chromatique est :"+nb);
	}
	
	public static void main(String args[])
	{
		Algo a1 = new Algo();
		int [][] matr = a1.mat();
		int deg[][] = a1.degree(matr);
		a1.tri(deg);
		a1.ada(deg,matr);
	}
}