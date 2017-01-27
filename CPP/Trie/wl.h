#ifndef WL_H
#define WL_H

void error();
bool error_size(int x, int y);
vector<string> get_user_input();
vector<string> file_to_string_vector(string file_name, bool &error);
vector<string> string_to_vector(string content);

#endif