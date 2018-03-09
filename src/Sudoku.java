
public class Sudoku {

	private int[][] jeu=new int[9][9];
	private int[][] solution=new int[9][9];
	
	public Sudoku(int nbEnlever){
		int lig, col, nb;
		String nbessayer;
		for(lig=0;lig<=8;lig++){
			for(col=0;col<=8;col++){
				nbessayer="123456789";
				do{
				nb=Integer.parseInt(Character.toString(nbessayer.charAt((int)Math.floor(Math.random()*nbessayer.length()))));
				nbessayer=nbessayer.substring(0,nbessayer.indexOf(Integer.toString(nb)))+nbessayer.substring(nbessayer.indexOf(Integer.toString(nb))+1);					
				}while(!possible(nb,lig,col));
				
				jeu[lig][col]=nb;
				solution[lig][col]=nb;
				
			}
		}
		
		
		enlever(nbEnlever);
	}
	
	public Sudoku(int [][]jeu){
		int lig,col;
		for(lig=0;lig<=8;lig++){
			for(col=0;col<=8;col++){
				this.jeu[lig][col]=jeu[lig][col];
				solution[lig][col]=jeu[lig][col];
			}
		}
		if(!solutionner(solution)){
			System.out.println("Pas de solution possible!");
		}
		
	}
	
	
	public void enlever(int nbEnlever){
		int lig,col, ligD=0, colD=0;
		boolean enlever=false;
		boolean trouverZero=false;
		for(lig=8;lig>=0&&!trouverZero;lig--){
			for(col=8;col>=0&&!trouverZero;col--){
				if(jeu[lig][col]==0){
					ligD=lig;
					colD=col;
					trouverZero=true;
				}
			}
		}
		do{
			do{
			lig=(int)Math.floor(Math.random()*9);
			col=(int)Math.floor(Math.random()*9);		
			}while(jeu[lig][col]==0);
			
			jeu[lig][col]=0;
			if(nbpossibilite(ligD,colD,jeu)!=1){
				jeu[lig][col]=solution[lig][col];
			}else{
				enlever=true;
			}		
		}while(!enlever);
		
		if(nbEnlever!=1){
			enlever(nbEnlever-1);
		}
		
		
	}
	
	public boolean possible(int nb, int lig, int col){
	int [][] jeu1=new int[9][9];	
		int lig1,col1;
		for(lig1=0;lig1<=8;lig1++){
			for(col1=0;col1<=8;col1++){
				jeu1[lig1][col1]=jeu[lig1][col1];
			}
		}
	if(coupPossible(nb, lig ,col)){
		
		jeu1[lig][col]=nb;
		
		return solutionner(jeu1);
	}else{
		return false;
	}
	
	}
	
	public void afficherJeu(){
	int lig1,lig2,col1,col2;	
	for(lig1=0;lig1<=2;lig1++){
		for(lig2=lig1*3;lig2<=(lig1*3)+2;lig2++){
			for(col1=0;col1<=2;col1++){
				for(col2=col1*3;col2<=(col1*3)+2;col2++){
					System.out.print(jeu[lig2][col2]);
				}
				System.out.print(" ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	}
	
	public void afficherSolution(){
		int lig1,lig2,col1,col2;	
		for(lig1=0;lig1<=2;lig1++){
			for(lig2=lig1*3;lig2<=(lig1*3)+2;lig2++){
				for(col1=0;col1<=2;col1++){
					for(col2=col1*3;col2<=(col1*3)+2;col2++){
						System.out.print(solution[lig2][col2]);
					}
					System.out.print(" ");
				}
				System.out.println("");
			}
			System.out.println("");
		}
		}
	
	
	
	public boolean solutionner(){
		int lig, col;
		for(col=0;col<jeu.length;col++){
			for(lig=0;lig<jeu.length;lig++){
				if(jeu[lig][col]==0){
					for(int nb=1;nb<=9;nb++){
						if(coupPossible(nb, lig, col)){
							jeu[lig][col]=nb;
							if(solutionner()){
								return true;
							}else{
								jeu[lig][col]=0;
							}
						}
					}
					return false;
				
				}
			}
		}
		return true;
	}
	
	public static int nbpossibilite(int ligDernier,int colDernier, int [][]jeu){
		int lig, col,lig1,col1, nbpossibilite=0; 
		boolean trouveZero=false;
		
		for(lig=0;lig<jeu.length&&!trouveZero;lig++){
			for(col=0;col<jeu.length&&!trouveZero;col++){
				if(jeu[lig][col]==0){
					trouveZero=true;
					if(lig==ligDernier&&col==colDernier){
						for(int nb=1;nb<=9;nb++){
							if(coupPossible(nb, lig, col, jeu)){
								return 1;
							}
						}
					}else{
						for(int nb=1;nb<=9;nb++){
						if(coupPossible(nb, lig, col, jeu)){
							int[][] jeu1=new int[9][9];
							for(lig1=0;lig1<=8;lig1++){
								for(col1=0;col1<=8;col1++){
									jeu1[lig1][col1]=jeu[lig1][col1];
								}
							}
							jeu1[lig][col]=nb;
							nbpossibilite=nbpossibilite+nbpossibilite(ligDernier,colDernier,jeu1);
							}
						}	
					}	
				}
			}
		}
		return nbpossibilite;
		
	}
	
	public static boolean solutionner(int jeu[][]){
		int lig, col;
		
		for(lig=0;lig<jeu.length;lig++){
			for(col=0;col<jeu.length;col++){
				if(jeu[lig][col]==0){
					for(int nb=1;nb<=9;nb++){
						if(coupPossible(nb, lig, col, jeu)){
							jeu[lig][col]=nb;
							if(solutionner(jeu)){
								return true;
							}else{
								jeu[lig][col]=0;
							}
						}
					}
					return false;
				
				}
			}
		}
		return true;
	}
	
	
	public boolean coupPossible(int nb,int lig, int col){
		int debutlig, debutcol, posx, posy;
		if(lig<=2){
			debutlig=0;
		}else{
			if(lig<=5){
				debutlig=3;
			}else{
				debutlig=6;
			}
		}
		
		if(col<=2){
			debutcol=0;
		}else{
			if(col<=5){
				debutcol=3;
			}else{
				debutcol=6;
			}
		}
		
		for(posx=debutcol;posx<=debutcol+2;posx++){
			for(posy=debutlig;posy<=debutlig+2;posy++){
				if(jeu[posy][posx]==nb){
					return false;
				}
			}
		}
		
		for(int pos=0;pos<=8;pos++){
			if(jeu[pos][col]==nb){
				return false;
			}
			
			if(jeu[lig][pos]==nb){
				return false;
			}
		}
		
		return true;
		
		
	}
	
	public static boolean coupPossible(int nb,int lig, int col, int [][]jeu){
		int debutlig, debutcol, posx, posy;
		if(lig<=2){
			debutlig=0;
		}else{
			if(lig<=5){
				debutlig=3;
			}else{
				debutlig=6;
			}
		}
		
		if(col<=2){
			debutcol=0;
		}else{
			if(col<=5){
				debutcol=3;
			}else{
				debutcol=6;
			}
		}
		
		for(posx=debutcol;posx<=debutcol+2;posx++){
			for(posy=debutlig;posy<=debutlig+2;posy++){
				if(jeu[posy][posx]==nb){
					return false;
				}
			}
		}
		
		for(int pos=0;pos<=8;pos++){
			if(jeu[pos][col]==nb){
				return false;
			}
			
			if(jeu[lig][pos]==nb){
				return false;
			}
		}
		
		return true;
		
		
	}
	
	public int[][] getJeu(){
		return jeu;
	}

}
