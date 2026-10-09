import { ModificationResult, PageInfo, PageResult, PageSearch, SubscriptionDetails, SubscriptionListItem, SubscriptionSearch, SubscriptionStatusForm } from "@/lib/types";

export async function search(form : SubscriptionSearch & PageSearch) : Promise<PageResult<SubscriptionListItem>> {
    return {
        contents: [dummyListItem],
        ...pageInfo
    }
}

export async function findById(id : string) : Promise<SubscriptionDetails> {
    return dummyDetails
}

export async function updateStatus(id : string, form : SubscriptionStatusForm) : Promise<ModificationResult<string>> {
    return {
        result : id
    }
}

const pageInfo: PageInfo = {
    page: 0,
    size: 10,
    totalCount: 15,
    totalPage : 2,
    links: [0, 1]
}

const dummyListItem: SubscriptionListItem = {
    id : "dummy",
    partnerName : "U Aung Kyaw",
    phone : "0981928372",
    email : "aungkyaw@gmail.com",
    company : "Alpha Romeo",
    planId : 2,
    planName : "Basic Plan",
    months : 3,
    status : "Pending",
    appliedAt : "2026-10-01 10:00",
    approvedAt : "",
    amount : 300000,
    subscribedAt : "2026-11-01",
    paidAt : "2026-10-01 10:00"
}

const dummyDetails : SubscriptionDetails = {
    ...dummyListItem,
    paymentProvider: "KBZ Pay",
    accountNo : "09782003098",
    accountName : "U Zaw Min Lwin",
    pamentSlip : "dummySlip",
    createdAt : "2026-10-01 10:00",
    createdBy : "U Aung Kyaw",
    modifiedAt: "",
    modifiedBy : ""
}