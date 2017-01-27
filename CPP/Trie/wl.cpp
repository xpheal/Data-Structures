#include <iostream>
#include <string>
#include <algorithm>
#include <sstream>
#include <vector>
#include <iterator>
#include <fstream>
#include <regex>
#include "tree.h"
#include "wl.h"

using namespace std;

int main() {
	Tree word_list = Tree();

	while(true){
		cout << ">";
		vector<string> inputs = get_user_input();
		string command = inputs.front();
		inputs.erase(inputs.begin());

		if(command == "end"){
			if(error_size(inputs.size(), 0)){
				continue;
			}

			return 0;
		}
		else if(command == "new"){
			if(error_size(inputs.size(), 0)){
				continue;
			}

			word_list.reset();
		}
		else if(command == "locate"){
			if(error_size(inputs.size(), 2)){
				continue;
			}

			string x = inputs.at(0);
			int occurence;

			try{
				occurence = stoi(inputs.at(1));
			} catch(const invalid_argument &err){
				error();
				continue;
			}

			if(occurence < 0){
				error();
				continue;
			}

			int num = word_list.locate(x, (size_t)occurence);

			if(num == -1){
				cout << "No matching entry" << endl;
				continue;
			}

			cout << num << endl;
		}
		else if(command == "load"){
			if(error_size(inputs.size(), 1)){
				continue;
			}

			bool error_exist = false;
			vector<string> list = file_to_string_vector(inputs.front(), error_exist);

			if(error_exist){
				error();
				continue;
			}

			for(string x : list){
				if(x.empty()){
					continue;
				}

				word_list.insert(x);
			}
		}
		else{
			error();
		}
	}
	
	return 0;
}

// Return the user input as a string vector
vector<string> get_user_input(){
	string input;
	getline(cin, input);
	transform(input.begin(), input.end(), input.begin(), ::tolower);

	istringstream ss_input(input);

	return vector<string>((istream_iterator<string>(ss_input)), istream_iterator<string>());
}

// Display error
void error(){
	cout << "ERROR: Invalid command" << endl;
}

// Display error and return true if int x does not equal to int y
bool error_size(int x, int y){
	if(x != y){
		error();
		return true;
	}
	return false;
}

// Parse the file given by file_name into a vector of strings, error = true if file not found
vector<string> file_to_string_vector(string file_name, bool &error){
	ifstream file(file_name);

	// File not found
	if(file.fail()){
		error = true;
		return vector<string>();
	}

	string content((istreambuf_iterator<char>(file)), istreambuf_iterator<char>());

	return string_to_vector(content);
}

vector<string> string_to_vector(string content){
	transform(content.begin(), content.end(), content.begin(), ::tolower);
	regex reg("[^a-zA-Z0-9]");
	sregex_token_iterator itr(content.begin(), content.end(), reg, -1);

	return vector<string>(itr, sregex_token_iterator());
}