import {combineReducers} from 'redux';
import accounts from './accounts'
import book_titles from './book_titles'
import book_title_edit from './book_title_edit'
import authors from './authors'
import list_new_book_titles from './list_new_book_titles'
import list_hot_book_titles from './list_hot_book_titles'
import type_books from './type_books'
import user_login from './user_login'
import publishers from './publishers'
import books from './books'
import roles from './roles'

const myReducer = combineReducers({
    accounts,
    book_titles,
    book_title_edit,
    authors,
    list_new_book_titles,
    list_hot_book_titles,
    type_books,
    user_login,
    publishers,
    books,
    roles
});

export default myReducer;