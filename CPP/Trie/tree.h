#ifndef TREE_H
#define TREE_H

#include <vector>
#include <string>

using namespace std;

int char_to_array_pos(char x);

struct Item{
	string key; // Word in the text
	vector<int> pos; // Position of the word in the text
};

struct TreeNode{
	Item *item;
	bool hasItem;
	TreeNode *children[36] = {NULL};
	// Pointer to children node, child[0] = 'a', child[1] = 'b' and so on, NULL if children does not exist
	// 26 chars + 10 digits
};

class Tree{
	private:
		int numItem;
		TreeNode *root;
	public:
		Tree();

		void insert(string x);
		int locate(string x, size_t occurence); // return -1 if string does not exist
		void reset();

		int getSize(){ return numItem; }
		int isEmpty(){ return numItem == 0; }
};

#endif