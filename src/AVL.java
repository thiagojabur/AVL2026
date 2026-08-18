import java.util.ArrayList;
import java.util.List;

public class AVL {
	Node root;
	List<Integer> aux = new ArrayList<>();
	
	public AVL(Node root) {
		this.root = root;
	}
	
	public int getSize() {
		return getSize(root);
	}
	
	public int getSize(Node x) {
		//faz a varredura e retorna a quantidade de nós
		if (x == null) 
			return 0;
			
		return 1 + getSize(x.getLeftNode())
				+ getSize(x.getRightNode()); 			
	}
	
	public void printPosOrder() {
		System.out.println("\nPost order");
		printPosOrder(root);
	}
	public void printPosOrder(Node x) {
		//Esquerda, Direita, Raiz
		if (x == null) 
			return;
		
			printPosOrder(x.getLeftNode());
			printPosOrder(x.getRightNode());
			System.out.print(x.getData() + " ");

	}
	
	public void printInOrder() {
		System.out.println("");
		printInOrder(root);
	}
	
	public void printInOrder(Node x) {
		//Esquerda, Raiz, Direito
		if (x == null) 
			return;
		
			
			printInOrder(x.getLeftNode());
			System.out.print(x.getData() + " ");
			aux.add(x.getData());
			printInOrder(x.getRightNode());
	}
	
	
	
	public void addNode(Node toAdd) {
		if (root == null) {
			root = toAdd;
		} else {
			addNode(root,toAdd);
		}	
	}
	
	public void addNode(Node aux, Node toAdd) {
		//condição de parada
		if (toAdd.getData() > aux.getData()) {
			if (aux.rightNode == null) {
			  aux.rightNode = toAdd;
			  toAdd.setDadNode(aux);
			  System.out.println("Inseriu " + toAdd + " Altura do pai:" + getHeight(aux));

			  return; //parada
			} else addNode(aux.rightNode, toAdd);
		}	
		else {
			if (aux.leftNode == null) {
			  aux.leftNode = toAdd; 
			  System.out.println("Inseriu " + toAdd + " Altura do pai:" + getHeight(aux));

			  return; //parada
			} else addNode(aux.leftNode, toAdd);
		}	
	}
	
	
	public void printPreOrder() {
		System.out.println("Pre order");
		printPreOrder(root);
	}
	
	public void printPreOrder(Node x) {
		//raiz primeiro, Esquerda, Direita
		if (x == null) 
			return;
		
			System.out.print(x.getData() + " ");
			printPreOrder(x.getLeftNode());
			printPreOrder(x.getRightNode());
			
	}

	
	public void listExternalNodes() {
		listExternalNodes(root);
	}
	
	public void listExternalNodes(Node x) {
		//raiz primeiro, Esquerda, Direita
		if (x == null) 
			return;
		
			if (x.getLeftNode() == null && x.getRightNode() == null )
				System.out.print(x.getData() + " ");
			
			listExternalNodes(x.getLeftNode());
			listExternalNodes(x.getRightNode());
			
	}
	
	public void listInternalNodes() {
		listInternalNodes(root);
	}
	
	public void listInternalNodes(Node x) {
		//raiz primeiro, Esquerda, Direita
		if (x == null) 
			return;
		
			if (x.getLeftNode() != null || x.getRightNode() != null )
				System.out.print(x.getData() + " ");
			
			listInternalNodes(x.getLeftNode());
			listInternalNodes(x.getRightNode());
			
	}
	
	public void delete(Node nodeToDelete) {
		
		//se tem dois filhos - ESSE que tá errado
		if ((nodeToDelete.getLeftNode()!= null) && (nodeToDelete.getRightNode()!= null )) {
			//subir o mais a direita da perna esquerda
			nodeToDelete.getLeftNode().rightNode = nodeToDelete.getRightNode();
			if (nodeToDelete == root)
				root = nodeToDelete.getLeftNode();
		}
		
		//se só perna para um lado
		if ( (nodeToDelete.getLeftNode() == null && nodeToDelete.getRightNode()!= null) 
				||
			 (nodeToDelete.getRightNode() == null && nodeToDelete.getLeftNode()!= null)) 
		{
			//pai dadNode tem que ligar no filho dele na perna certa
			
			Node child;
			if (nodeToDelete.getLeftNode() != null) {
				child = nodeToDelete.getLeftNode();
				nodeToDelete.dadNode.leftNode = child;	
			}	
			else {
				System.out.println("Caiu aqui apagando " + nodeToDelete + " pai dele é " + nodeToDelete.dadNode);
				child = nodeToDelete.getRightNode();
				
				nodeToDelete.dadNode.rightNode = child ;
				
				System.out.println(child);
			}
			
			//se tiver tentando apagar a raiz
			//o filho será a nova raiz e sai do método
			if (nodeToDelete == root) {
				child.dadNode = null;
				root = child;
				return;
			}  
				//child.dadNode = nodeToDelete.dadNode;  
				return;
	}
				
			
			
		
		if(nodeToDelete == root && root.isExternal()) {
		    root = null;
		    return;
		}
		
		//se não tem filho FUNCIONANDO
		if (nodeToDelete.isExternal()) {
			//achar o pai dele 
			//ver se ele é nó esquerdo ou direito e essa perna fica null
			if (nodeToDelete.dadNode.getLeftNode() == nodeToDelete) {
				nodeToDelete.dadNode.leftNode = null;	
			}	
			else {
				nodeToDelete.dadNode.rightNode = null;		
			}
			
		}
	}
	
	public boolean search(int valueToSearch) {	
		return search(valueToSearch, root);
	}
	

	private boolean search(int valor, Node x) {
	    if (x == null) return false;
	    if (valor == x.getData()) return true;
	    return valor < x.getData() 
	            ? search(valor, x.getLeftNode()) 
	            : search(valor, x.getRightNode());
	}	
	
	//- verificar se é estritamente binária (se tem 0 ou dois filhos função recursiva 
	public boolean isStrictBinaryTree() {
		return isStrictBinaryTree(root);
	}
	public boolean isStrictBinaryTree(Node x) {

	    if (x == null)
	        return true;

	    if ((x.getLeftNode() == null && x.getRightNode() != null) ||
	        (x.getLeftNode() != null && x.getRightNode() == null))
	        return false;

	    return isStrictBinaryTree(x.getLeftNode())
	            && isStrictBinaryTree(x.getRightNode());
	}
	public int getRoot() {
		return root == null ? null : root.getData();
	}
	
	public int depth (Node x) {
		  // calculo da profundidade do no x
		  if(x == root) 
		    return 0;
		  
		  return 1 + depth(x.dadNode);
	}
	
	public int getHeight () {
		return getHeight(root); 
	}
	//encontra altura da árvore
	//Se v é um no externo, então a altura de v  é 0.
	//Caso contrário, a altura de v é um mais a altura máxima dos filhos de v.
	//A altura total de uma árvore T é definida como a altura da raiz de T.

	
	public int getHeight (Node aux) {
	    if (aux == null) {
	        return -1;
	    } else 
	    	return Math.max(getHeight(aux.getLeftNode()), 
	    			getHeight(aux.getRightNode()))+1;  
	}

	public boolean isPerfectBalanced() {
		return isPerfectBalanced(root);
	}
	
	public boolean isPerfectBalanced(Node v) {
		//Condição de parada
		if (v == null) 
			return true;
		
		int bf = getSize(v.getLeftNode()) - getSize(v.getRightNode());
		
		return (Math.abs(bf)) <= 1 
				&& isPerfectBalanced(v.getLeftNode()) 
				&& isPerfectBalanced(v.getRightNode());
	
	}
	
	public boolean isBinarySearchTree() {
		 this.aux.clear();
		 printInOrder();
		 
		 for (int i=0; i<this.aux.size()-1; i++) {
			 if (aux.get(i) > aux.get(i+1)) {
				 
				 this.aux.clear();
				 return false;
			 }	 
		 }
		 this.aux.clear();
		 return true;
		
	}

	
	public boolean isBalanced() {
		return isBalanced(root);
	}
	
	public boolean isBalanced(Node v) {
		if (v == null) 
			return true;
		
		int bf = getHeight(v.getLeftNode()) - getHeight(v.getRightNode());
		
		return (Math.abs(bf)) <= 1 
				&& isBalanced(v.getLeftNode()) 
				&& isBalanced(v.getRightNode());
	}
	

}
