import * as types from '../constants/ActionType';
var initialState = [];

var myReducer = (state = initialState, action) => {
    switch (action.type) {
        case types.SET_LIST_AUTHOR:
            state = action.listAuthor;
            return [...state];
        case types.ADD_AUTHOR:
            return [...state, action.author];
        case types.EDIT_AUTHOR:
            let foundIndex = state.findIndex(x => x.id == action.author.id);
            let temp = [...state];
            temp[foundIndex] = action.author;
            return [...temp];
        case types.DELETE_AUTHOR:
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