import * as types from '../constants/ActionType';
var initialState = {};

var myReducer = (state = initialState, action) => {
    console.log(action);
    switch (action.type) {
        case types.SET_EDIT_BOOK_DATA:
            state = action.item;
            return {...state};
        default:
            return state;
    }
    return state;
}

export default myReducer;