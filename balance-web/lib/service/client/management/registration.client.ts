import { ModificationResult, PageInfo, PageResult, RegistrationDetails, RegistrationListItem, RegistrationPageSearch, RegistrationStatusForm } from "@/lib/types"

export async function search(form : RegistrationPageSearch) : Promise<PageResult<RegistrationListItem>> {
    return {
        contents: [dummyListItem],
        ...pageInfo
    }
}

export async function findById(id : string) : Promise<RegistrationDetails> {
    return dummyDetails
}

export async function updateStatus(id : string, form : RegistrationStatusForm) : Promise<ModificationResult<string>> {
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

const dummyListItem: RegistrationListItem = {
    id : "dummy",
    partnerName : "U Aung Kyaw",
    position : "Financial Executive",
    phone : "0981928372",
    email : "aungkyaw@gmail.com",
    company : "Alpha Romeo",
    companyType: "",
    companyAddress : "",
    status : "Pending",
    registeredAt : "2026-10-01 10:00",
    approvedAt : "",
}

const dummyDetails : RegistrationDetails = {
    ...dummyListItem,
    createdAt : "2026-10-01 10:00",
    createdBy : "U Aung Kyaw",
    modifiedAt: "",
    modifiedBy : ""
}