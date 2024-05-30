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
        return borrowRequestRepository.existsByClothingItemAndUser(clothingItem, user);
    }

    public BorrowRequest getBorrowRequest(Integer requestId) {
        return borrowRequestRepository.findById(requestId).get();
    }

    public void acceptBorrowRequest(BorrowRequest borrowRequest) {
        borrowRequest.setRequestStatus(RequestStatus.ACCEPTED);
    }
}