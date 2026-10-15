import MemberTemplate from "@/components/widgets/pages/member-template";

export default function LedgerDetailsPage() {
    return (
        <MemberTemplate page="Details" links={[
            {name : "Ledger Management", route : "/member/organization/ledger"}
        ]}>
            <></>
        </MemberTemplate>
    )
}