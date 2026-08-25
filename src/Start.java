
public class Start {

	public static void main(String[] args) {
		System.out.println("Árvore AVL - 2026");
		Node cinquenta = new Node(50);
		Node quarenta = new Node(40);
		Node trinta = new Node(30);
		Node sessenta = new Node(60);
		Node setenta = new Node(70);
		Node vinteecinco = new Node(25);
		Node vinteesete = new Node(27);
		Node oitenta = new Node(80);
		Node setentaecinco = new Node(75);
		Node setentaesete = new Node(77);
		Node oitentaeum = new Node(81);
		
		AVL arvoreExemplo 
		= new AVL(cinquenta);
		System.out.println("Root " + arvoreExemplo.getRoot());  
		
		arvoreExemplo.addNode(quarenta);
		arvoreExemplo.addNode(trinta);
		arvoreExemplo.addNode(sessenta);
		arvoreExemplo.addNode(setenta);
		arvoreExemplo.addNode(vinteecinco);
		arvoreExemplo.addNode(vinteesete);
		arvoreExemplo.addNode(oitenta);
		arvoreExemplo.addNode(setentaecinco);
		arvoreExemplo.addNode(setentaesete);
		arvoreExemplo.delete(setentaesete);
		arvoreExemplo.addNode(oitentaeum);
		arvoreExemplo.delete(oitenta);
		
		System.out.println("\nNúmero de elementos: ");
		System.out.println(arvoreExemplo.getSize());
		arvoreExemplo.printInOrder();
		
		System.out.println("\nBF(40) = " + quarenta.bf);

		System.out.println("\nBF(75) = " + setentaecinco.bf);

		System.out.println("Árvore está balanceada? " + arvoreExemplo.isBalanced());
	}
}
