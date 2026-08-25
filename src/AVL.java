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
        if (x == null)
            return;

        printInOrder(x.getLeftNode());
        System.out.print(x.getData() + " ");
        aux.add(x.getData());
        printInOrder(x.getRightNode());
    }

    public int calculateBF(Node node) {

        if (node == null)
            return 0;

        int bf = getHeight(node.getLeftNode())
                - getHeight(node.getRightNode());

        return bf;
    }


    public void addNode(Node toAdd) {

        if (root == null) {
            root = toAdd;
        } else {
            addNode(root, toAdd);
        }
    }

    public void addNode(Node aux, Node toAdd) {

        if (toAdd.getData() > aux.getData()) {
            if (aux.rightNode == null) {
                //chegou numa folha
            	aux.rightNode = toAdd;
                toAdd.setDadNode(aux);
            } else {
            	//não é folha, vai descer
                addNode(aux.rightNode, toAdd);
            }
        } else if (toAdd.getData() < aux.getData()) { //para esquerda
            if (aux.leftNode == null) {
                aux.leftNode = toAdd;
                toAdd.setDadNode(aux);
            } else 
                addNode(aux.leftNode, toAdd);
        } else { 
        	System.out.println("Valor repetido não pode ser inserido.");
        	return; 
        }

        // Desempilhamento da recursão.
        // Atualiza o fator de balanceamento.
        aux.setBF(calculateBF(aux));

        //chama as rotações
        Node nodeA;
        Node nodeB;

        // Verifica se o nó ficou desbalanceado
        if (aux.getBF() == -2 || aux.getBF() == 2) {

            //descobriu o A
        	nodeA = aux;

            if (nodeA.getBF() == -2) {
                //B está no lado direito
            	nodeB = nodeA.getRightNode();

                // RR
                if (nodeB.getBF() == -1) {
                    System.out.println("RotationRR");
                    RotationRR(nodeA, nodeB);
                }

                // RL
                else if (nodeB.getBF() == 1) {
                    System.out.println("RotationRL");
                    RotationRL(nodeA, nodeB);
                }
            }

            else {
                nodeB = nodeA.getLeftNode();
                // LL
                if (nodeB.getBF() == 1) {
                    System.out.println("RotationLL");
                    RotationLL(nodeA, nodeB);
                }

                // LR
                else if (nodeB.getBF() == -1) {
                    System.out.println("RotationLR");
                    RotationLR(nodeA, nodeB);
                }
            }
        }
    }

    public void RotationRR(Node nodeA, Node nodeB) {

        Node dad = nodeA.dadNode;
        Node aux = nodeB.leftNode;

        // B ocupa o lugar que A ocupava
        if (dad == null) { //é a raiz
            root = nodeB;
        } else if (dad.leftNode == nodeA) {
            dad.leftNode = nodeB;
        } else 
            dad.rightNode = nodeB;
      
        // Atualiza pai de B
        nodeB.dadNode = dad;

        // A passa a ser filho esquerdo de B
        nodeB.leftNode = nodeA;
        nodeA.dadNode = nodeB;

        // Subárvore intermediária
        nodeA.rightNode = aux;

        if (aux != null) {
            aux.dadNode = nodeA;
        }

        // Atualiza fatores
        nodeA.setBF(calculateBF(nodeA));
        nodeB.setBF(calculateBF(nodeB));
    }

    public void RotationLL(Node nodeA, Node nodeB) {

        Node dad = nodeA.dadNode;
        Node aux = nodeB.rightNode;

        // B ocupa o lugar que A ocupava
        if (dad == null) {
            root = nodeB;
        } else if (dad.leftNode == nodeA) {
            dad.leftNode = nodeB;
        } else {
            dad.rightNode = nodeB;
        }

        // Atualiza pai de B
        nodeB.dadNode = dad;

        // A passa a ser filho direito de B
        nodeB.rightNode = nodeA;
        nodeA.dadNode = nodeB;

        // Subárvore intermediária
        nodeA.leftNode = aux;

        if (aux != null) {
            aux.dadNode = nodeA;
        }

        // Atualiza fatores
        nodeA.setBF(calculateBF(nodeA));
        nodeB.setBF(calculateBF(nodeB));
    }

    public void RotationLR(Node nodeA, Node nodeB) {

        // B possui C à direita
        Node nodeC = nodeB.rightNode;

        // Primeiro RR em B
        RotationRR(nodeB, nodeC);

        // Depois LL em A
        RotationLL(nodeA, nodeC);
    }


    public void RotationRL(Node nodeA, Node nodeB) {

        // B possui C à esquerda
        Node nodeC = nodeB.leftNode;

        // Primeiro LL em B
        RotationLL(nodeB, nodeC);

        // Depois RR em A
        RotationRR(nodeA, nodeC);
    }

    public void printPreOrder() {
        printPreOrder(root);
    }

    public void printPreOrder(Node x) {

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

        if (x == null)
            return;

        if (x.getLeftNode() == null &&
            x.getRightNode() == null) {

            System.out.print(x.getData() + " ");
        }

        listExternalNodes(x.getLeftNode());
        listExternalNodes(x.getRightNode());
    }

    public void listInternalNodes() {
        listInternalNodes(root);
    }

    public void listInternalNodes(Node x) {

        if (x == null)
            return;

        if (x.getLeftNode() != null ||
            x.getRightNode() != null) {

            System.out.print(x.getData() + " ");
        }

        listInternalNodes(x.getLeftNode());
        listInternalNodes(x.getRightNode());
    }


    public void delete(Node nodeToDelete) {

        // Se tem dois filhos
        if ((nodeToDelete.getLeftNode() != null) &&
            (nodeToDelete.getRightNode() != null)) {

            nodeToDelete.getLeftNode().rightNode =
                    nodeToDelete.getRightNode();

            if (nodeToDelete == root)
                root = nodeToDelete.getLeftNode();
        }

        // Se possui apenas um filho
        if ((nodeToDelete.getLeftNode() == null &&
             nodeToDelete.getRightNode() != null)
                ||
            (nodeToDelete.getRightNode() == null &&
             nodeToDelete.getLeftNode() != null)) {

            Node child;

            if (nodeToDelete.getLeftNode() != null) {

                child = nodeToDelete.getLeftNode();
                nodeToDelete.dadNode.leftNode = child;

            } else {

                System.out.println(
                    "Caiu aqui apagando " +
                    nodeToDelete +
                    " pai dele é " +
                    nodeToDelete.dadNode
                );

                child = nodeToDelete.getRightNode();

                nodeToDelete.dadNode.rightNode = child;

                System.out.println(child);
                //atualizando BF de todos os pais
                updateDadsBF(nodeToDelete.dadNode);
            
            }

            // Apagando a raiz
            if (nodeToDelete == root) {

                child.dadNode = null;
                root = child;

                return;
            }

            return;
        }

        // Árvore com apenas um nó
        if (nodeToDelete == root &&
            root.isExternal()) {

            root = null;

            return;
        }

        // Nó folha
        if (nodeToDelete.isExternal()) {
        	
            if (nodeToDelete.dadNode.getLeftNode()
                    == nodeToDelete) {
                nodeToDelete.dadNode.leftNode = null;
            } else {
                nodeToDelete.dadNode.rightNode = null;
            }
            
            //atualizando BF de todos os pais
            updateDadsBF(nodeToDelete.dadNode);
        
        }
    }
    
    public void updateDadsBF(Node dad) {
    	//condição de parada 
    	if (dad == null)
    		return;
    	
    	dad.setBF(calculateBF(dad));
    	
    	//chamar ela mesma 
    	updateDadsBF(dad.getDadNode());
    	
    }	
    
    

    public boolean search(int valueToSearch) {

        return search(valueToSearch, root);
    }

    private boolean search(int valor, Node x) {

        if (x == null)
            return false;

        if (valor == x.getData())
            return true;

        return valor < x.getData()
                ? search(valor, x.getLeftNode())
                : search(valor, x.getRightNode());
    }

    // =========================================================
    // ÁRVORE BINÁRIA ESTRITA
    // =========================================================

    public boolean isStrictBinaryTree() {

        return isStrictBinaryTree(root);
    }

    public boolean isStrictBinaryTree(Node x) {

        if (x == null)
            return true;

        if ((x.getLeftNode() == null &&
             x.getRightNode() != null)
                ||
            (x.getLeftNode() != null &&
             x.getRightNode() == null)) {

            return false;
        }

        return isStrictBinaryTree(x.getLeftNode())
                && isStrictBinaryTree(x.getRightNode());
    }

    // =========================================================
    // RAIZ
    // =========================================================

    public int getRoot() {

        return root == null ? null : root.getData();
    }

    public int depth(Node x) {

        if (x == root)
            return 0;

        return 1 + depth(x.dadNode);
    }


    public int getHeight() {

        return getHeight(root);
    }

    public int getHeight(Node aux) {

        if (aux == null) {
            return -1;
        }

        return Math.max(
                getHeight(aux.getLeftNode()),
                getHeight(aux.getRightNode())
        ) + 1;
    }

    public boolean isPerfectBalanced() {
        return isPerfectBalanced(root);
    }

    public boolean isPerfectBalanced(Node v) {

        if (v == null)
            return true;

        int bf = getSize(v.getLeftNode())
                - getSize(v.getRightNode());

        return Math.abs(bf) <= 1
                && isPerfectBalanced(v.getLeftNode())
                && isPerfectBalanced(v.getRightNode());
    }


    public boolean isBinarySearchTree() {

        this.aux.clear();
        printInOrder();
        for (int i = 0; i < this.aux.size() - 1; i++) {

            if (aux.get(i) > aux.get(i + 1)) {

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

        int bf = getHeight(v.getLeftNode())
                - getHeight(v.getRightNode());

        return Math.abs(bf) <= 1
                && isBalanced(v.getLeftNode())
                && isBalanced(v.getRightNode());
    }
}