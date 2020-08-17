import * as types from '../constants/ActionType';
var initialState = [];

var myReducer = (state = initialState, action) => {
    switch (action.type) {
        case types.SET_LIST_BOOK:
            state = action.listBook;
            return [...state];
        case types.DELETE_BOOK:
            console.log("state in delete book", state);
            console.log("item in delete book", action.item)
            const filteredItems = state.filter(function(item) {
                return item !== action.item
              })
              console.log("delete book", filteredItems);
            return [...filteredItems];
        default:
            return state;

    }
    return state;
}

export default myReducer;