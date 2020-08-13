import * as types from '../constants/ActionType';

export const setListAccount = (listAccount) =>{
    return {
        type: types.SET_LIST_ACCOUNT,
        listAccount,
    }
}

export const setListBookTitle = (listBookTitle) =>{
    return {
        type: types.SET_LIST_BOOK_TITLE,
        listBookTitle,
    }
}

export const setBookTitleEdit = (item) =>{
    return {
        type: types.SET_EDIT_BOOK_DATA,
        item,
    }
}

export const setListAuthor = (listAuthor) =>{
    return {
        type: types.SET_LIST_AUTHOR,
        listAuthor,
    }
}