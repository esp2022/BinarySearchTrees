# BinarySearchTrees
Compile Command:
javac BinarySearchTreeDriver.java

Run Commands: 
java BinarySearchTreeDriver int-input.txt
java BinarySearchTreeDriver string-input.txt
java BinarySearchTreeDriver double-input.txt

Eeshwar Potluri esp94422@uga.edu
Responsible for insert
Responsible for inOrder
Responsible for BinarySearchsTree

Rohan Kothari rk56153@uga.edu
Responsible for delete
Responsible for retrieve



1. getSingleParent function Pseudocode:
    function getSingleParent(node)
        if node is null
            return
        if node has only one child
            print node's value
        getSingleParent(node's left child)
        getSingleParent(node's right child)
    Time complexity: O(n), where n is the number of nodes in the BST, as each node is visited once.

2. getNumLeafNodes function Pseudocode:
    function getNumLeafNodes(node)
        if node is null
            return 0
        if node has no children
            return 1
        return getNumLeafNodes(node's left child) + getNumLeafNodes(node's right child)
    Time complexity: O(n), where n is the number of nodes in the BST, as each node is visited once.

3. getCousins function Pseudocode:
    function getCousins(item)
        targetNode = findNode(root, item)
        if targetNode is null
            print "Node not found"
            return
        parentNode = getParent(root, item)
        targetLevel = getLevel(root, parentNode's value, 0)
        getCousinsAtLevel(root, parentNode, targetLevel, targetNode's value)

    function getCousinsAtLevel(node, parent, level, target)
        if node is null or level < 0
            return
        if level == 0
            print non-parent sibling nodes
            return
        getCousinsAtLevel(node's left child, parent, level - 1, target)
        getCousinsAtLevel(node's right child, parent, level - 1, target)
    Time complexity: O(n), where n is the number of nodes in the BST, as each node is visited once in the worst case.
