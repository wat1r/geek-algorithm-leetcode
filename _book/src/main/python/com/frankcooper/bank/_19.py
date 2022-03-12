class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
    dummy = ListNode(-1)  # 定义一个哑结点
    dummy.next = head  ##将哑结点放在链表的头部
    slow, fast = dummy, dummy  ## 初始化时，快慢指针都指向哑结点
    for i in range(n):  # 调整快指针的位置，使其与慢指针的距离相差n
        fast = fast.next
    while fast.next:  # 游走快慢指针，当快指针走到链表的末尾时，慢指针的next节点指向的是待删除的节点
        slow = slow.next
        fast = fast.next
    slow.next = slow.next.next  # 开始删除
    return dummy.next  # 返回头节点
