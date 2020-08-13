import {combineReducers} from 'redux';
import accounts from './accounts'
import book_titles from './book_titles'
import book_title_edit from './book_title_edit'
import authors from './authors'
import list_new_book_titles from './list_new_book_titles'
import list_hot_book_titles from './list_hot_book_titles'

const myReducer = combineReducers({
    accounts,
    book_titles,
    book_title_edit,
    authors,
    list_new_book_titles,
    list_hot_book_titles
});

export default myReducer;