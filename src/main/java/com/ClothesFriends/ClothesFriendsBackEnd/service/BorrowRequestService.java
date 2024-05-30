package com.ClothesFriends.ClothesFriendsBackEnd.service;

import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem.BorrowRequest;
import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem.ClothingItem;
import com.ClothesFriends.ClothesFriendsBackEnd.model.ClothingItem.RequestStatus;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.BorrowRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowRequestService {
    @Autowired
    private BorrowRequestRepository borrowRequestRepository;

    public BorrowRequest createBorrowRequest(User user, ClothingItem clothingItem){
        BorrowRequest borrowRequest = new BorrowRequest();
        borrowRequest.setUser(user);
        borrowRequest.setClothingItem(clothingItem);
        borrowRequestRepository.save(borrowRequest);
        return borrowRequest;
    }

    public Boolean wasRequested(ClothingItem clothingItem, User user) {
        BorrowRequest borrowRequest = borrowRequestRepository.getBorrowRequestByClothingItemAndUser(clothingItem, user);
        if (borrowRequest == null || borrowRequest.getRequestStatus() == RequestStatus.REJECTED) {
            return false;
        }
        return true;
    }

    public BorrowRequest getBorrowRequest(Integer requestId) {
        return borrowRequestRepository.findById(requestId).get();
    }

    public void acceptBorrowRequest(BorrowRequest borrowRequest) {
        borrowRequest.setRequestStatus(RequestStatus.ACCEPTED);
    }

    public void rejectBorrowRequest(BorrowRequest borrowRequest) {
        borrowRequest.setRequestStatus(RequestStatus.REJECTED);
    }

    public Boolean wasHandled(Integer borrowRequestId) {
        BorrowRequest borrowRequest = borrowRequestRepository.findById(borrowRequestId).get();
        return borrowRequest.getRequestStatus() == RequestStatus.ACCEPTED || borrowRequest.getRequestStatus() == RequestStatus.REJECTED;
    }
}
