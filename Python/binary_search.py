def binary_search(arr, target):
	if not arr:
		return None

	lower_bound = 0
	upper_bound = len(arr) - 1

	while lower_bound <= upper_bound:
		mid = (lower_bound + upper_bound) // 2

		if arr[mid] == target:
			return mid
		elif target > arr[mid]:
			lower_bound = mid + 1
		else:
			upper_bound = mid - 1

	return None

def test(func, expected_output, *args):
	output = func(*args)
	if output != expected_output:
		print("TEST FAILED:\nACTUAL OUTPUT:{}\nEXPECTED OUTPUT:{}".format(output, expected_output))
	else:
		print("TEST PASSED")

# Test
arr = [1,2,3,4,5]

test(binary_search, 0, arr, 1)
test(binary_search, 1, arr, 2)
test(binary_search, 2, arr, 3)
test(binary_search, 3, arr, 4)
test(binary_search, 4, arr, 5)
test(binary_search, None, arr, -59)
test(binary_search, None, arr, 105)

arr = [-5, 20, 77, 100]

test(binary_search, 0, arr, -5)
test(binary_search, 1, arr, 20)
test(binary_search, 2, arr, 77)
test(binary_search, 3, arr, 100)
test(binary_search, None, arr, -12314)
test(binary_search, None, arr, 54453)

test(binary_search, None, [], 5)
test(binary_search, None, None, 10)
