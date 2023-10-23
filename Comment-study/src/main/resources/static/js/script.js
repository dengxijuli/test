// 获取评论容器和评论输入框
var commentContainer = document.getElementById('comment-container');
var commentInput = document.getElementById('comment-input');

// 发表评论按钮点击事件
document.getElementById('comment-submit').addEventListener('click', function() {
    var commentContent = commentInput.value.trim();
    if (commentContent !== '') {
        // 发送评论请求并更新评论列表
        // ...
        // 在回调函数中更新评论列表
        // updateCommentList(...);a
    }
});

// 回复按钮点击事件（事件委托）
commentContainer.addEventListener('click', function(event) {
    if (event.target.classList.contains('reply-btn')) {
        var commentElement = event.target.closest('.comment');
        var replyListElement = commentElement.querySelector('.reply-list');
        var replyInput = document.createElement('textarea');
        replyInput.placeholder = '写下你的回复...';
        replyInput.classList.add('reply-input');
        var replyButton = document.createElement('button');
        replyButton.textContent = '回复';

        // 回复按钮点击事件
        replyButton.addEventListener('click', function() {
            var replyContent = replyInput.value.trim();
            if (replyContent !== '') {
                // 发送回复请求并更新回复列表
                // ...
                // 在回调函数中更新回复列表
                // updateReplyList(...);
            }
        });

        var replyItem = document.createElement('li');
        replyItem.classList.add('reply');
        replyItem.appendChild(replyInput);
        replyItem.appendChild(replyButton);

        replyListElement.appendChild(replyItem);
    }
});

// 点赞按钮点击事件（事件委托）
commentContainer.addEventListener('click', function(event) {
    if (event.target.classList.contains('like-btn')) {
        var likeButton = event.target;
        var likeCount = likeButton.nextElementSibling;
        var currentLikes = parseInt(likeCount.textContent);
        if (likeButton.classList.contains('liked')) {
            likeButton.classList.remove('liked');
            likeCount.textContent = currentLikes - 1;
        } else {
            likeButton.classList.add('liked');
            likeCount.textContent = currentLikes + 1;
        }
    }
});

// 收起按钮点击事件（事件委托）
commentContainer.addEventListener('click', function(event) {
    if (event.target.classList.contains('fold-btn')) {
        var commentBody = event.target.parentNode.previousElementSibling;
        commentBody.classList.add('folded');
        event.target.style.display = 'none';
        event.target.nextElementSibling.style.display = 'inline-block';
    }
});

// 展开按钮点击事件（事件委托）
commentContainer.addEventListener('click', function(event) {
    if (event.target.classList.contains('expand-btn')) {
        var commentBody = event.target.parentNode.previousElementSibling;
        commentBody.classList.remove('folded');
        event.target.style.display = 'none';
        event.target.previousElementSibling.style.display = 'inline-block';
    }
});

// 更新评论列表函数
function updateCommentList(comments) {
    var commentListElement = commentContainer.querySelector('.comment-list');
    commentListElement.innerHTML = '';

    comments.forEach(function(comment, index) {
        var commentElement = document.createElement('li');
        commentElement.classList.add('comment');

        var avatarElement = document.createElement('div');
        avatarElement.classList.add('avatar');
        var avatarImgElement = document.createElement('img');
        avatarImgElement.src = comment.avatar;
        avatarImgElement.alt = '用户头像';
        avatarElement.appendChild(avatarImgElement);

        var commentContentElement = document.createElement('div');
        commentContentElement.classList.add('comment-content');

        var commentHeaderElement = document.createElement('div');
        commentHeaderElement.classList.add('comment-header');
        var usernameElement = document.createElement('span');
        usernameElement.classList.add('username');
        usernameElement.textContent = comment.username;
        var floorElement = document.createElement('span');
        floorElement.classList.add('floor');
        floorElement.textContent = (index + 1) + '楼';
        var timeElement = document.createElement('span');
        timeElement.classList.add('time');
        timeElement.textContent = comment.time;
        commentHeaderElement.appendChild(usernameElement);
        commentHeaderElement.appendChild(floorElement);
        commentHeaderElement.appendChild(timeElement);

        var commentBodyElement = document.createElement('div');
        commentBodyElement.classList.add('comment-body');
        commentBodyElement.textContent = comment.content;

        var commentFooterElement = document.createElement('div');
        commentFooterElement.classList.add('comment-footer');
        var replyButton = document.createElement('button');
        replyButton.classList.add('reply-btn');
        replyButton.textContent = '回复';
        var likeButton = document.createElement('button');
        likeButton.classList.add('like-btn');
        likeButton.textContent = '点赞';
        var foldButton = document.createElement('button');
        foldButton.classList.add('fold-btn');
        foldButton.textContent = '收起';
        var expandButton = document.createElement('button');
        expand
    })
}