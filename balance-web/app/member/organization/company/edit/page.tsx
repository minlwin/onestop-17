import MemberTemplate from "@/components/widgets/pages/member-template";

export default function CompanyProfilePage() {
    return (
        <MemberTemplate page="Edit" links={[
            {name : "Company Profile", route : "/member/organization/company"}
        ]}>
            <></>
        </MemberTemplate>
    )
}