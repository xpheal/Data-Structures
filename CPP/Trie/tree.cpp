#include <iostream>
#include "tree.h"

using namespace std;

int char_to_array_pos(char x){
	int curr = (int)x;
	if(curr >= 48 && curr <= 57){
		return curr - 22;
	}
	else if(curr >= 97 && curr <= 122){
		return curr - 97;
	}
	else{
		return -1;
	}
}

Tree::Tree(){
	reset();
}

void Tree::insert(string x){
	TreeNode *currNode = root;
	string keys(x);

	while(!keys.empty()){
		int curr = char_to_array_pos(keys[0]);

		if(curr == -1){
			cout << "ERROR: invalid character (shouldn't occur)" << endl;
			return;
		}

		keys.erase(0, 1);

		if(currNode->children[curr] == NULL){
			currNode->children[curr] = new TreeNode();
			currNode = currNode->children[curr];
		}
		else{
			currNode = currNode->children[curr];
		}
	}

	if(!currNode->hasItem){
		currNode->hasItem = true;
		currNode->item = new Item;
		currNode->item->key = x;
	}
	
	currNode->item->pos.push_back(numItem + 1);
	numItem ++;
}

// Return -1 if not found
int Tree::locate(string x, size_t occurence){
	TreeNode *currNode = root;
	string keys(x);

	while(!keys.empty()){
		int curr = char_to_array_pos(keys[0]);

		if(curr == -1){
			cout << "ERROR: invalid character (shouldn't occur)" << endl;
			return -1;
		}

		keys.erase(0, 1);

		if(currNode->children[curr] == NULL){
			return -1;
		}
		currNode = currNode->children[curr];
	}

	if(!currNode->hasItem){
		return -1;
	}

	if(currNode->item->pos.size() < occurence){
		return -1;
	}

	return currNode->item->pos[occurence - 1];
}

void Tree::reset(){
	numItem = 0;
	root = new TreeNode();
}