import sys

G = int(sys.stdin.readline())
P = int(sys.stdin.readline())
plist = [int(sys.stdin.readline()) for _ in range(P)]

used_gate = dict()

# Find max no. of available gate below limit
def findGate(limit: int) -> int:
    for l in range(limit, -1, -1):
        if not (used_gate.get(l, False)):
            return l
    else:
        return False

result = 0
for plane in plist:
    gate = findGate(plane)

    if (gate):
        used_gate[gate] = True
        result += 1
    else:
        break

print(result)
