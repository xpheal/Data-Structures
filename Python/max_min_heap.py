# A heap (max or min) written in Python to solve a Hackerrank problem

class Heap(object):
    def __init__(self, is_min_heap=True):
        self.heap = [None]
        self.is_min_heap = is_min_heap
        
    def __len__(self):
        return len(self.heap) - 1
    
    def empty(self):
        return len(self) == 0
    
    def peek(self):
        if self.empty():
            return None
        
        return self.heap[1]
    
    def push(self, num):
        self.heap.append(num)
        
        i = len(self)
        
        while i > 1:
            i_parent = i // 2
            
            if self.is_min_heap:
                if self.heap[i] < self.heap[i_parent]:
                    self.heap[i], self.heap[i_parent] = self.heap[i_parent], self.heap[i]
                else:
                    break
            else:
                if self.heap[i] > self.heap[i_parent]:
                    self.heap[i], self.heap[i_parent] = self.heap[i_parent], self.heap[i]
                else:
                    break
                
            i = i // 2
            
    def pop(self):
        if self.empty():
            return None
        
        ret_val = self.peek()
        
        self.heap[1] = self.heap[-1]
        del self.heap[-1]
        
        i = 1
        i_child = i * 2
        heap_length = len(self.heap)
        
        while i_child < heap_length:
            if i_child + 1 < heap_length:
                if self.is_min_heap:
                    if self.heap[i_child + 1] < self.heap[i_child]:
                        i_child = i_child + 1
                else:
                    if self.heap[i_child + 1] > self.heap[i_child]:
                        i_child = i_child + 1
            
            if self.is_min_heap:
                if self.heap[i] > self.heap[i_child]:
                    self.heap[i], self.heap[i_child] = self.heap[i_child], self.heap[i]
                else:
                    break
            else:
                if self.heap[i] < self.heap[i_child]:
                    self.heap[i], self.heap[i_child] = self.heap[i_child], self.heap[i]
                else:
                    break
                
            i = i_child
            i_child = i * 2
            
        return ret_val