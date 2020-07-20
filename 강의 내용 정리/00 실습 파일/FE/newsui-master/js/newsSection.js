export default class newsSection {
    constructor(fetchUrl) {
        this.fetchUrl = fetchUrl;
    }

    init(fnNewsListTemplate, fnNewsCompaniesList) {
        this.fetchData();
        this.fnNewsListTemplate = fnNewsListTemplate;
        this.fnNewsCompaniesList = fnNewsCompaniesList;
    }
    
    fetchData() {
        fetch(this.fetchUrl)
        .then(response => response.json())
        .then(result => {
            this.makeNewsContents(result);
            this.makeCompanyList(result);
        })
    }

    makeNewsContents(newsList) {
        let html = '';
        newsList.forEach(news => {
            const templating = this.fnNewsListTemplate(news);
            html += templating;
        });
        const content = document.querySelector('.mainArea .content');
        content.innerHTML = html;
    }

    makeCompanyList(newsList) {
        let html = '';
        newsList.forEach(news => {
            const obj = { company: news.company, id: news.id, logoImgUrl: news.logoImgUrl };
            const templating = this.fnNewsCompaniesList(obj);
            html += templating;
        })
        const company = document.querySelector('.mainArea .newsNavigation');
        company.innerHTML = html;
    }
}
