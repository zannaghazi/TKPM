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

export const addNewAuthor = (author) =>{
    return {
        type: types.ADD_AUTHOR,
        author,
    }
}

export const setListNewBookTitle = (listNewBookTitle) =>{
    return {
        type: types.SET_LIST_NEW_BOOK_TITLE,
        listNewBookTitle,
    }
}

export const setHotNewBookTitle = (listHotBookTitle) =>{
    return {
        type: types.SET_LIST_HOT_BOOK_TITLE,
        listHotBookTitle,
    }
}

export const setListTypeBook = (listTypeBook) =>{
    return {
        type: types.SET_LIST_TYPE_BOOK,
        listTypeBook,
    }
}

export const setUserLogin = (user) =>{
    return {
        type: types.SET_USER_LOGIN,
        user,
    }
}

export const editAuthor = (author) =>{
    return {
        type: types.EDIT_AUTHOR,
        author,
    }
}

export const setListPublisher = (list)=>{
    return {
        type: types.SET_LIST_PUBLISHER,
        list,
    }
}

export const addNewPublisher = (pulisher) =>{
    return {
        type: types.ADD_PUBLISHER,
        pulisher,
    }
}

export const editPublisher = (publisher) =>{
    return {
        type: types.EDIT_PUBLISHER,
        publisher,
    }
}

export const setListBook = (listBook) =>{
    return {
        type: types.SET_LIST_BOOK,
        listBook,
    }
}