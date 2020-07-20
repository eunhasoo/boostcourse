import newsSection from './newsSection.js'
import {fnNewsListTemplate} from '../templates/news.js'
import {fnCompaniesListTemplate} from '../templates/companies.js'

document.addEventListener("DOMContentLoaded", () => { // arrow function
    const url = "/data/newslist.json";
    const news = new newsSection(url);
    news.init(fnNewsListTemplate, fnCompaniesListTemplate);
});