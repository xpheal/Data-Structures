#include <stdio.h>
#include <stdlib.h>
#include "cachequeue.h"

// Dequeue without returning item
void just_dequeue(cache_queue *q){
	// Increment head
	q->head = (q->head + 1) % q->max_item;

	// Reset queue to empty state if it is empty
	if(q->head == q->tail){
		q->head = -1;
		q->tail = 0;
	}

	q->num_items --;
}

// Return 1 if an item is eviceted while enqueing, return 0 if successful without eviction
int enqueue(cache_queue *q, cache_line_t item){
	if(q->tail % q->max_item == q->head){
		// Queue is full, have to dequeue an item
		just_dequeue(q);
		enqueue(q, item);
		return 1;
	}
	else{
		// Add item to queue
		q->ptr[q->tail] = item;
		q->tail = (q->tail + 1) % q->max_item;

		if(q->head == -1){
			q->head = 0;
		}

		q->num_items ++;
		return 0;
	}
}

// Return 1 if queue is empty and 0 otherwise
int is_empty(cache_queue *q){
	if(q->head == -1){
		return 1;
	}
	else{
		return 0;
	}
}

// Dequeue the least recently used item from the queue into the parameter cache_line_t, return 1 if successful(Not empty), return 0 if fail
int dequeue(cache_queue *q, cache_line_t* item){
	if(is_empty(q)){
		return 0;
	}	
	else{
		// Pass the item
		item->valid = q->ptr[q->head].valid;
		item->tag = q->ptr[q->head].tag;

		// Increment head
		q->head = (q->head + 1) % q->max_item;

		// Reset queue to empty state if it is empty
		if(q->head == q->tail){
			q->head = -1;
			q->tail = 0;
		}

		q->num_items --;
		return 1;
	}
}

// Return 1 if tag is in cache, else return 0
int item_exist(cache_queue *q, mem_addr_t tag){
	int i = 0;
	for(i = 0; i < q->num_items; i++){
		int ctr = (q->head + i) % q->max_item;
		if(q->ptr[ctr].tag == tag){
			return 1;
		}
	}
	return 0;
}

// Debug by printing out the state of the queue
void debug_queue(cache_queue *q){
	printf("Idx: %d", q->set_index);

	int i = 0;
	for(i = 0; i < q->num_items; i++){
		int ctr = (q->head + i) % q->max_item;
		printf("[%d:%llu]", q->ptr[ctr].valid, q->ptr[ctr].tag);
	}

	printf("\n");
}

// Update item in queue, this means, dequeue and enqueue the item so that it is the most recently used
void update_queue(cache_queue *q, mem_addr_t tag){
	int i = 0, ctr = 0;
	cache_line_t cache_holder;
	int item_found = 0;

	for(i = 0; i < q->num_items - 1; i++){
		ctr = (q->head + i) % q->max_item;

		if(!item_found){
			// Item is not found yet
			if(q->ptr[ctr].tag == tag){
				// Found the item
				cache_holder = q->ptr[ctr];
				q->ptr[ctr] = q->ptr[(ctr + 1) % q->max_item];
				item_found = 1;
			}
		}
		else{
			// Left shift the array
			q->ptr[ctr] = q->ptr[(ctr + 1) % q->max_item];
		}
	}

	// Add the item add the front of the queue
	if(item_found){
		q->ptr[(ctr + 1) % q->max_item] = cache_holder;
	}
}

// Initialize the queue
cache_queue* init_queue(int max_item, int set_index){
	cache_queue *q = malloc(sizeof(cache_queue));
	q->max_item = max_item;
	q->ptr = malloc(sizeof(cache_line_t) * max_item);
	q->head = -1;
	q->tail = 0;
	q->num_items = 0;
	q->set_index = set_index;
	return q;
}

// Free the queue in the memory
void free_queue(cache_queue* q){
	free(q->ptr);
	free(q);
}