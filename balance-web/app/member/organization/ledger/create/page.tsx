import MemberTemplate from "@/components/widgets/pages/member-template";

export default function CreateLedgerPage() {
    return (
        <MemberTemplate page="Create" links={[
            {name : "Ledger Management", route : "/member/organization/ledger"}
        ]}>
            <></>
        </MemberTemplate>
    )
}