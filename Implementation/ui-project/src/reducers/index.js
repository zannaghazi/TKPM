import {combineReducers} from 'redux';
import accounts from './accounts'
import book_titles from './book_titles'
import book_title_edit from './book_title_edit'

const myReducer = combineReducers({
    accounts,
    book_titles,
    book_title_edit
});

export default myReducer;