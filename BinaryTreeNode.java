// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman

public class BinaryTreeNode<dataType>
{
   BinaryDataFields data;
   BinaryTreeNode left;
   BinaryTreeNode right;
   int height;
   
   public BinaryTreeNode (BinaryDataFields d, BinaryTreeNode<dataType> l, BinaryTreeNode<dataType> r )
   {
      data = d;
      left = l;
      right = r;
      height = 0;
   }
   
   BinaryTreeNode getLeft () { return left; }
   BinaryTreeNode getRight () { return right; }
}
