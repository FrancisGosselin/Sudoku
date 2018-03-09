import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		Sudoku sudoku=new Sudoku(Integer.parseInt(JOptionPane.showInputDialog("Indiquez le nombre d'éléments à enlever (max 56)")));
		sudoku.afficherJeu();
		sudoku.afficherSolution();

	}

}
