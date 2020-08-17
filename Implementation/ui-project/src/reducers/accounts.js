import * as types from '../constants/ActionType';
var initialState = [];

var myReducer = (state = initialState, action) => {
    switch (action.type) {
        case types.SET_LIST_ACCOUNT:
            state = action.listAccount;
            return [...state];

        case types.DELETE_ACCOUNT:
            const filteredItems = state.filter(function (item) {
                return item !== action.item
            })
            return [...filteredItems];
        default:
            return state;

    }
    return state;
}

export default myReducer;