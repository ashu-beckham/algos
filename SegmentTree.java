/*
--> Segment trees are used for performing multiple range queries
with update operations in minimum time.
-->Segment Tree is a basically a binary tree used for storing the intervals or segments.
  Each node in the Segment Tree represents an interval.
  * Consider an array A of size N and a corresponding Segment Tree T :
  i)  The root of T will represent the whole array A[0:N−1].
  ii) Each leaf in the Segment Tree T will represent a single element A[i] such that 0≤i<N .
  iii) The internal nodes in the Segment Tree T represents the union of elementary intervals A[i:j] where 0≤i<j<N.

--> There are 3 operations associated with segment tree.
  i) for initializing the segment tree.   Time complexity : O(n)
  ii) update :upadting all the nodes of the segment tree whenever there is a update in the main array.  Time complexity : O(long(n))
  iii) query(l,R) : answering the query in the given range from l to R .    Time complexity : O(long(n))
*/
//Note
/*
The program is written for this example :
* Given an array A of size N, there are two types of queries on this array.
1. qlr : In this query you need to print the minimum in the sub-array A[l:r]
2. uxy : In this query you need to update A[x]=y.

This type of queries are called Range Minimum Query.

*******************************
Please provide the folowing input while running the program :
5 5              (size of array and number of queries )
1 5 2 4 3        (elements of the array )
q 1 5             (query for range 1 to 5)
q 1 3
q 3 5
u 3 6             (update for range 3 to 6)
q 1 5

Excepted output ;
1
1
2
1

example referance :Hcakerearth .
*/

import java.io.*;
import java.util.*;

class SegmentTree {
  static int[] seg;                           //declaring the segment tree .
  static int[] a;                             // declaring the base array .
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n=in.nextInt(),q=in.nextInt();
    a=new int[n];
    for(int i=0;i<n;i++)
      a[i]=in.nextInt();                    //initializing base array
    seg=new int[4*n+1];
    build(1,0,n-1);   //using build function to initialize the segment tree

    while (q-->0){                //executing queries
      String s=in.next();
      if (s.equals("u"))                                 // u is to update
        update(1,0,n-1,in.nextInt()-1,in.nextInt());    //updating the segment tree
      else
        System.out.println(query(1,0,n-1,in.nextInt()-1,in.nextInt()-1));  //performing the query to find min in range l to r.
    }
    in.close();
    }
  static void build(int node,int start,int end){
    // Build function uses bottom to up approach
    if (start==end)
     // Leaf node will have a single element
      seg[node]=a[start];                                   //first leaves are filled then , using leaves recusively other nodes are filled
    else{
      int mid=(start+end)/2;
    // Recurse on the left child
      build(2*node,start,mid);
    // Recurse on the right child
      build(2*node+1,mid+1,end);
    // Internal node will have the min of both of its children
      seg[node]=Math.min(seg[2*node],seg[2*node+1]);
    }
  }
  static void update(int node,int start,int end,int ind,int val){
    if (start==end){
      // Leaf node
      seg[node]=val;

      a[ind]=val;
      return;
    }
    int mid=(start+end)/2;
    // If ind is in the left child, recurse on the left child
    if (start<=ind && mid>=ind)
      update(2*node,start,mid,ind,val);
    // if ind is in the right child, recurse on the right child
    else
      update(2*node+1,mid+1,end,ind,val);

    // Internal node will have the min of both of its children
    seg[node]=Math.min(seg[2*node],seg[2*node+1]);

  }
  /*
  To query on a given range, check 3 conditions.

    * Range represented by a node is completely inside the given range
    * Range represented by a node is completely outside the given range
    * Range represented by a node is partially inside and partially outside the given range

  */
  static int query(int node,int start,int end,int l,int r){

    // range represented by a node is completely outside the given range
    if (start>r || end <l)
      return Integer.MAX_VALUE;

    // range represented by a node is completely inside the given range
    if (l<=start && end<=r)
      return seg[node];

    // range represented by a node is partially inside and partially outside the given range
    int mid=(start+end) /2;
    return Math.min(query(2*node,start,mid,l,r),query(2*node+1,mid+1,end,l,r));
  }

}
