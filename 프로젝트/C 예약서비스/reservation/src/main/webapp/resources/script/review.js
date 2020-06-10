var reviewApp = {
    res: '',
    setRes(res) {
        this.res = res;
    },
    init() {
        var urlParams = new URLSearchParams(window.location.search);
        var req = new XMLHttpRequest();
        req.open('GET', '/reservation/api/products/' + urlParams.get('id'));
        req.onload = function() {
            var jsonRes = JSON.parse(req.response);
            window.reviewApp.setRes(jsonRes.displayInfoResponse);
            window.reviewApp.setTitleAndGrade();
            window.reviewApp.showComments();
            window.reviewApp.btnBack();
        }
        req.send();
    },
    setTitleAndGrade() {
        var grade = this.res.averageScore.toFixed(1);
        document.querySelector('.review_header a.title').innerText = this.res.displayInfo.productDescription;
        document.querySelector('.short_review_area em.graph_value').style.width = (grade * 20) + '%';
        document.querySelector('.short_review_area .text_value span').innerText = grade;
        document.querySelector('.short_review_area em.green').innerText = this.res.comments.length + '건';
    },
    showComments() {
        var context = [];
        var comments = this.res.comments;
        for (var i = 0; i < comments.length; i++) {
                var commentImage, commentImageCount;
                if (comments[i].userCommentImage === null) {
                    commentImage = false;
                    commentImageCount = 0;
                } else {
                    commentImage = comments[i].userCommentImage.saveFileName;
                    commentImageCount = 1;
                }
                var obj = {
                    "productDescription": this.res.displayInfo.productDescription,
                    "commentImage": commentImage,
                    "commentImageCount": commentImageCount,
                    "comment": comments[i].comment,
                    "score": comments[i].score,
                    "date": comments[i].reservationDate.match(/(\d+)-(\d+)-(\d+)/g)[0].split('-').join('.'),
                    "id": comments[i].email.slice(0, 4).concat('', '****')
                }
                context.push(obj);
            }
        var template = document.querySelector('#comment-list').innerText;
        var bindTemplate = Handlebars.compile(template);
        var bindHTML = context.reduce((prev, current) => {
            return prev + bindTemplate(current);
        }, '');
        var ul = document.querySelector('.section_review_list .list_short_review').innerHTML = bindHTML;
    },
    btnBack() {
      document.querySelector('.top_title .btn_back').addEventListener('click', () => {
        window.location.href = '/reservation/detail?id=' + this.res.displayInfo.displayInfoId;
      });
    }
}

document.addEventListener('DOMContentLoaded', () => {
    reviewApp.init();
});