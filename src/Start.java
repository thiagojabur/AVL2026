
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
		
		arvoreExemplo.addNode(um);
		
		arvoreExemplo.addNode(tres);
		Node vinte = new Node(20);
		arvoreExemplo.addNode(vinte);
		Node sete = new Node(7);
		arvoreExemplo.addNode(sete);
		arvoreExemplo.addNode(quatro);

		System.out.println("A arvore com:  " + arvoreExemplo.getSize() + " elemento");
		
		System.out.println("É estritamente Binária " + arvoreExemplo.isStrictBinaryTree());
		
		arvoreExemplo.printPreOrder();
		arvoreExemplo.printPosOrder();
		arvoreExemplo.printInOrder();
		System.out.println("\nNúmero de elementos: ");
		System.out.println(arvoreExemplo.getSize());
	
		System.out.println("Folhas");
		arvoreExemplo.listExternalNodes();
		System.out.println("\nNós Internos");
		arvoreExemplo.listInternalNodes();
		
		System.out.println("Buscou 6 " + arvoreExemplo.search(6));
			
		System.out.println("Altura da árvore: " + arvoreExemplo.getHeight());
		
		System.out.println("Altura da subárvore 2: " + arvoreExemplo.getHeight(um));
		System.out.println("Altura da subárvore 3: " + arvoreExemplo.getHeight(tres));

		System.out.print("Arvore é balanceada? ");
		System.out.println(arvoreExemplo.isBalanced());
	    
		System.out.print("Arvore é Perfeitamente balanceada? ");
		System.out.println(arvoreExemplo.isPerfectBalanced());

		arvoreExemplo.printInOrder();
		
		System.out.println("Altura do 2: " + arvoreExemplo.getHeight(raiz));
		
	}
}
