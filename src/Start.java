
public class Start {

	public static void main(String[] args) {
		System.out.println("Árvore AVL - 2026");
		Node raiz = new Node(2);
		Node um = new Node(1);
		Node tres = new Node(3);
		Node quatro = new Node(4);
		AVL arvoreExemplo 
		= new AVL(raiz);
		System.out.println("Root " + arvoreExemplo.getRoot());  
		
			
		arvoreExemplo.addNode(tres);
		Node vinte = new Node(20);
		arvoreExemplo.addNode(vinte);
		
		System.out.println("\nNúmero de elementos: ");
		System.out.println(arvoreExemplo.getSize());
		arvoreExemplo.printInOrder();
		
		System.out.println("BF(3) = " + tres.bf);
		System.out.println("BF(2) = " + raiz.bf);
		System.out.println("BF(20) = " + vinte.bf);	
	}
}
