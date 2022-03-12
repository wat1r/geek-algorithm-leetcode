from typing import List


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


def rightSideView(self, root: TreeNode) -> List[int]:
    res = []
    if not root: return res
    queue = [root]
    while queue:
        size = len(queue)
        res.append(queue[-1].val)
        for _ in range(size):
            cur = queue.pop(0)
            if cur.left: queue.append(cur.left)
            if cur.right: queue.append(cur.right)
    return res
