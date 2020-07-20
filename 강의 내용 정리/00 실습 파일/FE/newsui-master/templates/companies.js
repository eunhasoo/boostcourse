const fnCompaniesListTemplate = ({company, id, logoImgUrl}) => {
    return `<div class="newsCompany">
    <div class="img-title-wrap">
        <img src="${logoImgUrl}" alt="">
    </div>`;
}

export {fnCompaniesListTemplate}