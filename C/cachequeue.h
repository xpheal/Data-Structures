typedef unsigned long long int mem_addr_t;

typedef struct cache_line {
    char valid;
    mem_addr_t tag;
} cache_line_t;

typedef struct Queue{
	int max_item;
	cache_line_t *ptr;
	int head;
	int tail;
	int num_items;
	int set_index;
} cache_queue;

void just_dequeue(cache_queue *queue);
int enqueue(cache_queue *queue, cache_line_t item);
int is_empty(cache_queue *queue);
int dequeue(cache_queue *queue, cache_line_t* item);
int item_exist(cache_queue *q, mem_addr_t tag);
void debug_queue(cache_queue *q);
void update_queue(cache_queue *q, mem_addr_t tag);
cache_queue* init_queue(int max_item, int set_index);
void free_queue(cache_queue* q);